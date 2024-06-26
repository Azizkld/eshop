package com.billcom.eshop.Request;

import com.billcom.eshop.commons.Enum.ServiceType;
import lombok.Data;

@Data
public class ServiceNameRequest {

    private String snDesc;

    private String snName;

    private ServiceType snType;
}
