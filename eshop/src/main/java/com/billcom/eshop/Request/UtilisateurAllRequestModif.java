package com.billcom.eshop.Request;

import lombok.Data;

@Data
public class UtilisateurAllRequestModif {
    private String utAdresse;
    private String utCity;
    private String utCountry;
    private String utMail;
    private String utPassword;
    private Integer utZipCode;
    private String currentPassword; // Pour la vérification du mot de passe actuel
}
