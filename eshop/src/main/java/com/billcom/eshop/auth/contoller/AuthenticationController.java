package com.billcom.eshop.auth.contoller;

import com.billcom.eshop.auth.models.LoginRequest;
import com.billcom.eshop.auth.models.LoginResponse;
import com.billcom.eshop.auth.models.RegisterRequest;
import com.billcom.eshop.auth.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping(value = "/register", consumes = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<LoginResponse> register(
            @RequestParam("firstname") String firstname,
            @RequestParam("cin") String cin,
            @RequestParam("lastname") String lastname,
            @RequestParam("email") String email,
            @RequestParam("password") String password ,
            @RequestParam("utCinFrontImage") MultipartFile utCinFrontImage,
            @RequestParam("utCinBackImage") MultipartFile utCinBackImage
    ) {
        RegisterRequest request = RegisterRequest.builder()
                .firstname(firstname)
                .cin(cin)
                .lastname(lastname)
                .email(email)
                .password(password)
               .utCinFrontImage(utCinFrontImage)
                .utCinBackImage(utCinBackImage)
                .build();
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<LoginResponse> authenticate(
            @RequestBody LoginRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }

}
