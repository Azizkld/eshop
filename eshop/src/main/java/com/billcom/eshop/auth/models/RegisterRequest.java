package com.billcom.eshop.auth.models;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String firstname;
    private String cin;
    private String lastname;
    private String email;
    private String password;
    private String adresse;
    private String country;
    private String city;
    private Integer zipCode;



}
