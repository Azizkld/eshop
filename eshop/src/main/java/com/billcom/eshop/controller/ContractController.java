package com.billcom.eshop.controller;

import com.billcom.eshop.Request.ContractRequest;
import com.billcom.eshop.Responce.ContractResponse;
import com.billcom.eshop.service.ContractService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SecurityRequirement(name = "bearer-key")
@RestController
@RequestMapping("/api/v1/Contract")
public class ContractController {

    @Autowired
    private ContractService contractService;

    @PostMapping("/ajouterContract")
    public ResponseEntity<ContractResponse> ajouterContract(@RequestBody ContractRequest contractRequest) {
        ContractResponse contractResponse = contractService.ajouterContract(contractRequest);
        if (contractResponse.getIsSuccessfull()) {
            return ResponseEntity.ok(contractResponse);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(contractResponse);
        }
    }

    @GetMapping("/findAllContract")
    public ResponseEntity<ContractResponse> findAllContract() {
        ContractResponse contractResponse = contractService.findAllContract();
        if (contractResponse.getIsSuccessfull()) {
            return ResponseEntity.ok(contractResponse);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(contractResponse);
        }
    }
    @GetMapping("/findAllContractById/{id}")
    public ResponseEntity<ContractResponse> findAllContractById(@PathVariable Long id) {
        ContractResponse contractResponse = contractService.findAllContractById(id);
        if (contractResponse.getIsSuccessfull()) {
            return ResponseEntity.ok(contractResponse);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(contractResponse);
        }
    }
}
