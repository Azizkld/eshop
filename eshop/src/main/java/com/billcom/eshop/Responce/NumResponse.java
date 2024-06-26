package com.billcom.eshop.Responce;

import com.billcom.eshop.commons.entities.Num;
import lombok.Data;

import java.util.List;

@Data
public class NumResponse {
    private Boolean isSuccessfull;
    private String message;
    private Num num;
    private List<Num> nums;

    public NumResponse() {
    }
}
