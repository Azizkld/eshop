package com.billcom.eshop.controller;

import com.billcom.eshop.InterfaceService.InterfaceNumAjoutService;
import com.billcom.eshop.Request.NumAjoutRequest;
import com.billcom.eshop.Responce.NumAjoutResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SecurityRequirement(name = "bearer-key")
@CrossOrigin("*")
@RestController
@RequestMapping("/api/Num")
public class NumAjoutController {
    @Autowired
    private InterfaceNumAjoutService numAjoutService;

    @PostMapping("/addNum")
    public ResponseEntity<NumAjoutResponse> ajouterNumero(@RequestBody NumAjoutRequest numAjoutRequest) {
        NumAjoutResponse numAjoutResponse = numAjoutService.ajouterNumero(numAjoutRequest);
        if (numAjoutResponse.getIsSuccessfull()) {
            return ResponseEntity.ok(numAjoutResponse);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(numAjoutResponse);
        }
    }
}
