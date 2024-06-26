package com.billcom.eshop.Responce;

import com.billcom.eshop.commons.entities.Num;
import lombok.Data;

@Data
public class NumAjoutResponse {
    private Boolean isSuccessfull;
    private String message;
    private Num num;
}
