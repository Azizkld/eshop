package com.billcom.eshop.Responce;

import com.billcom.eshop.commons.entities.Claim;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ClaimResponse {
    private Boolean isSuccessfull;
    private  String message;
    private List<Map<String, Object>> claims; // Assurez-vous que cela correspond au format renvoyé
    private Claim claim;

}
