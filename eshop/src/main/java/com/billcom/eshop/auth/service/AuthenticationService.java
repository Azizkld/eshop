package com.billcom.eshop.auth.service;


import com.billcom.eshop.auth.token.Token;
import com.billcom.eshop.auth.token.TokenRepository;
import com.billcom.eshop.auth.models.LoginRequest;
import com.billcom.eshop.auth.models.LoginResponse;
import com.billcom.eshop.auth.models.RegisterRequest;
import com.billcom.eshop.auth.token.TokenType;
import com.billcom.eshop.auth.models.Role;
import com.billcom.eshop.commons.dtos.UtilisateurAllDto;
import com.billcom.eshop.commons.entities.UtilisateurAll;
import com.billcom.eshop.commons.mappers.UtilisateurAllMapper;
import com.billcom.eshop.commons.repositories.UtilisateurAllRepository;
import com.billcom.eshop.service.ImageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UtilisateurAllRepository repository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UtilisateurAllMapper utilisateurAllMapper;
    private final ImageService imageService;  // Add ImageService

    public LoginResponse register(RegisterRequest request) {
        // Handle image uploads
        String frontImageName = imageService.getAlphaNumericString(20) + StringUtils.cleanPath(request.getUtCinFrontImage().getOriginalFilename());
        String backImageName = imageService.getAlphaNumericString(20) + StringUtils.cleanPath(request.getUtCinBackImage().getOriginalFilename());

        imageService.uploadToLocalFileSystem(request.getUtCinFrontImage(), frontImageName);
        imageService.uploadToLocalFileSystem(request.getUtCinBackImage(), backImageName);

        UtilisateurAllDto user = UtilisateurAllDto.builder()
                .utFName(request.getFirstname())
                .utLName(request.getLastname())
                .utCin(request.getCin())
                .utPassword(passwordEncoder.encode(request.getPassword()))
                .utMail(request.getEmail())
                .utStatus(false)
                .role(Role.USER)
                .utCinFrontImage(frontImageName)
                .utCinBacktImage(backImageName)
                .build();

        UtilisateurAll savedUser = this.repository.save(this.utilisateurAllMapper.toEntity(user));
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(savedUser, jwtToken);
        return LoginResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    public LoginResponse authenticate(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getCin(),
                        request.getPassword()
                )
        );
        UtilisateurAll utilisateurAll = repository.findByUtCin(request.getCin()).orElseThrow(() -> new RuntimeException("Utilisateur pas trouve"));
        if (!utilisateurAll.getUtStatus()) {
            throw new RuntimeException("Le statut de l'utilisateur n'est pas activé. Veuillez contacter l'administrateur.");
        }
        UtilisateurAllDto user = this.utilisateurAllMapper.toDto(utilisateurAll);
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(utilisateurAll, jwtToken);
        return LoginResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    private void saveUserToken(UtilisateurAll user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(UtilisateurAllDto user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(Math.toIntExact(user.getId()));
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            UtilisateurAll user = this.repository.findByUtCin(userEmail)
                    .orElseThrow();

            UtilisateurAllDto utilisateurAllDto = this.utilisateurAllMapper.toDto(user);
            if (jwtService.isTokenValid(refreshToken, utilisateurAllDto)) {
                var accessToken = jwtService.generateToken(utilisateurAllDto);
                revokeAllUserTokens(utilisateurAllDto);
                saveUserToken(user, accessToken);
                var authResponse = LoginResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }
}