package com.billcom.eshop.Responce;

import com.billcom.eshop.commons.entities.UtilisateurAll;
import lombok.Data;

import java.util.List;

@Data
public class UtilisateurAllResponse {
    private boolean isSuccessfull;
    private String message;
    private UtilisateurAll utilisateurAll;
    private List<UtilisateurAll> utilisateursAlls;
}
