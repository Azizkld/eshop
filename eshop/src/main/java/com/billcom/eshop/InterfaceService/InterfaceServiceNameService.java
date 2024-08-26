package com.billcom.eshop.InterfaceService;

import com.billcom.eshop.Request.ServiceNameRequest;
import com.billcom.eshop.Responce.ServiceNameResponse;

import java.util.List;

public interface InterfaceServiceNameService {
    ServiceNameResponse ajouterService(ServiceNameRequest serviceNameRequest);
    List<ServiceNameResponse> findAllServiceName();  // Nouvelle méthode


}
