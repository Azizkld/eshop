package com.billcom.eshop.Responce;

import com.billcom.eshop.commons.entities.Claim;
import lombok.Data;

import java.util.List;

@Data
public class ClaimResponse {
    private Boolean isSuccessfull;
    private  String message;
    private List<Claim> claims;
    private Claim claim;
}
