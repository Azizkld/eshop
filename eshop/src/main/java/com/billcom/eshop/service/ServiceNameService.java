package com.billcom.eshop.service;

import com.billcom.eshop.InterfaceService.InterfaceServiceNameService;
import com.billcom.eshop.Request.ServiceNameRequest;
import com.billcom.eshop.Responce.ServiceNameResponse;
import com.billcom.eshop.commons.entities.ServiceName;
import com.billcom.eshop.commons.repositories.ServiceNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceNameService implements InterfaceServiceNameService {

    @Autowired
    private ServiceNameRepository serviceNameRepository;

    @Override
    public ServiceNameResponse ajouterService(ServiceNameRequest serviceNameRequest) {
        ServiceNameResponse serviceNameResponse = new ServiceNameResponse();

        ServiceName serviceName = new ServiceName();
        serviceName.setSnDesc(serviceNameRequest.getSnDesc());
        serviceName.setSnName(serviceNameRequest.getSnName());
        serviceName.setSnType(serviceNameRequest.getSnType());

        serviceNameRepository.save(serviceName);

        serviceNameResponse.setIsSuccessfull(true);
        serviceNameResponse.setServiceName(serviceName);
        serviceNameResponse.setMessage("Service added successfully");

        return serviceNameResponse;
    }
}
