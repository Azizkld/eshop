package com.billcom.eshop.InterfaceService;

import com.billcom.eshop.Request.ServiceNameRequest;
import com.billcom.eshop.Responce.ServiceNameResponse;

public interface InterfaceServiceNameService {
    ServiceNameResponse ajouterService(ServiceNameRequest serviceNameRequest);

}
