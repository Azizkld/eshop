package com.billcom.eshop.controller;

import com.billcom.eshop.Request.ServiceNameRequest;
import com.billcom.eshop.Responce.ServiceNameResponse;
import com.billcom.eshop.service.ServiceNameService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "bearer-key")
@CrossOrigin("*")
@RestController
@RequestMapping("/api/ServiceName")
public class ServiceNameController {

    @Autowired
    private ServiceNameService serviceNameService;

    @PostMapping("/addService")
    public ResponseEntity<ServiceNameResponse> ajouterService(@RequestBody ServiceNameRequest serviceNameRequest) {
        ServiceNameResponse serviceNameResponse = serviceNameService.ajouterService(serviceNameRequest);
        return ResponseEntity.ok(serviceNameResponse);
    }

    @GetMapping("/findAllServiceName")
    public ResponseEntity<List<ServiceNameResponse>> findAllServiceName() {
        List<ServiceNameResponse> serviceNameResponses = serviceNameService.findAllServiceName();
        return ResponseEntity.ok(serviceNameResponses);
    }
}
