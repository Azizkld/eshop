package com.billcom.eshop.Request;

import lombok.Data;

@Data
public class UtilisateurAllRequest {
    private String utAdresse;
    private String utCity;
    private String utCountry;
    private String utFName;
    private String utLName;
    private String utMail;
    private Integer utZipCode;
    private String image;
    private String utPassword;
    private String currentPassword;  // Pour la validation du mot de passe actuel

}
