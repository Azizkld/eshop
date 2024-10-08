package com.billcom.eshop.auth.models;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

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

    // New fields for image uploads
  private MultipartFile utCinFrontImage;
  private MultipartFile utCinBackImage;




}
