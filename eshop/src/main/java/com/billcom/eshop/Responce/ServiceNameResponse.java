package com.billcom.eshop.Responce;

import com.billcom.eshop.commons.entities.ServiceName;
import lombok.Data;

@Data
public class ServiceNameResponse {

    private Boolean isSuccessfull;
    private String message;
    private ServiceName serviceName;
}
