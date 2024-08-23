package com.billcom.eshop.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.util.pattern.PathPatternParser;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> {
                    auth
                            .requestMatchers("/api/v1/auth/**","/swagger-ui/**","/api-docs/**")
                            .permitAll()
                            .anyRequest()
                            .authenticated();
                })
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(httpSecurityLogoutConfigurer -> {
                    httpSecurityLogoutConfigurer
                            .logoutUrl("/api/v1/auth/logout")
                            .addLogoutHandler(logoutHandler)
                            .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext());
                });

        return http.build();
    }

//    @Bean
//    public CorsConfigurationSource corsWebFilter() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowCredentials(true);
//        configuration.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
//        configuration.addAllowedHeader("*");
//        configuration.addExposedHeader("*");
//        configuration.addAllowedMethod("*");
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new PathPatternParser());
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }


}
