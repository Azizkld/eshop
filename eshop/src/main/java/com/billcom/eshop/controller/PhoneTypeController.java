package com.billcom.eshop.controller;

import com.billcom.eshop.Request.PhoneTypeRequest;
import com.billcom.eshop.Responce.PhoneTypeResponse;
import com.billcom.eshop.service.PhoneTypeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SecurityRequirement(name = "bearer-key")
@CrossOrigin("*")
@RestController
@RequestMapping("/api/PhoneTYPE")
public class PhoneTypeController {

    @Autowired
    private PhoneTypeService phoneTypeService;

    @PostMapping("/addPhoneTYPE")
    public ResponseEntity<PhoneTypeResponse> ajoutTypePhone(@RequestBody PhoneTypeRequest phoneTypeRequest) {
        PhoneTypeResponse phoneTypeResponse = phoneTypeService.ajoutTypePhone(phoneTypeRequest);
        return ResponseEntity.ok(phoneTypeResponse);
    }

    @GetMapping("/findAllPhoneType")
    public ResponseEntity<PhoneTypeResponse> findAllPhoneType() {
        PhoneTypeResponse phoneTypeResponse = phoneTypeService.findAllPhoneType();
        return ResponseEntity.ok(phoneTypeResponse);
    }
}
