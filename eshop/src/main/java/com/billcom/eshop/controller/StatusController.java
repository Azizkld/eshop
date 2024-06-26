package com.billcom.eshop.controller;

import com.billcom.eshop.InterfaceService.InterfaceStatusService;
import com.billcom.eshop.Request.StatusRequest;
import com.billcom.eshop.Responce.StatusResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SecurityRequirement(name = "bearer-key")
@RestController
@RequestMapping("/api/status")
@CrossOrigin("*")
public class StatusController {
    @Autowired
    private InterfaceStatusService statusService;

    @PostMapping("/change")
    public ResponseEntity<StatusResponse> changeUserStatus(@RequestBody StatusRequest statusRequest) {
        StatusResponse response = statusService.changeUserStatus(statusRequest);
        return ResponseEntity.ok(response);
    }
}
