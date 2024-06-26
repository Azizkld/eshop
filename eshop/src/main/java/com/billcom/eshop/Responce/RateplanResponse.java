package com.billcom.eshop.Responce;

import com.billcom.eshop.commons.entities.Rateplan;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class RateplanResponse {
    private Boolean isSuccessfull;
    private String message;
    private Rateplan rateplan;
    private List<Map<String, Object>> Rateplans;
    private List<String> serviceNames;
}
