package com.billcom.eshop.Responce;

import com.billcom.eshop.commons.entities.PhoneType;
import lombok.Data;

@Data
public class PhoneTypeResponse {
    private Boolean isSuccessfull;
    private String message;
    private PhoneType phoneType;
}
