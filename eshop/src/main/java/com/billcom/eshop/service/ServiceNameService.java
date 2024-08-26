package com.billcom.eshop.service;

import com.billcom.eshop.InterfaceService.InterfaceServiceNameService;
import com.billcom.eshop.Request.ServiceNameRequest;
import com.billcom.eshop.Responce.ServiceNameResponse;
import com.billcom.eshop.commons.entities.ServiceName;
import com.billcom.eshop.commons.repositories.ServiceNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        serviceNameResponse.setSnDesc(serviceName.getSnDesc());
        serviceNameResponse.setSnName(serviceName.getSnName());
        serviceNameResponse.setSnType(serviceNameResponse.getSnType());
        serviceNameResponse.setMessage("Service added successfully");

        return serviceNameResponse;
    }

    @Override
    public List<ServiceNameResponse> findAllServiceName() {
        List<ServiceName> services = serviceNameRepository.findAll();

        return services.stream().map(service -> {
            ServiceNameResponse response = new ServiceNameResponse();
            response.setSnDesc(service.getSnDesc());
            response.setSnName(service.getSnName());
            response.setSnType(service.getSnType());
            return response;
        }).collect(Collectors.toList());
    }
}
