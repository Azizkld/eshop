package com.billcom.eshop.Request;

import lombok.Data;

import java.util.Set;

@Data
public class RateplanRequest {
    private String rpDesc;
    private String rpName;
    private String rpPrice;
    private Integer rpValidationDays;
    private Set<Long> serviceIds;
}
