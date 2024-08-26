package com.billcom.eshop.Responce;

import com.billcom.eshop.commons.Enum.ServiceType;
import com.billcom.eshop.commons.entities.ServiceName;
import lombok.Data;

@Data
public class ServiceNameResponse {

    private Boolean isSuccessfull;
    private String message;


    private String snDesc;
    private String snName;
    private ServiceType snType;
}
