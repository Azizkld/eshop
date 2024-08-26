package com.billcom.eshop.controller;

import com.billcom.eshop.Request.NumRequest;
import com.billcom.eshop.Request.NumRequestAcheterSim;
import com.billcom.eshop.Responce.NumResponse;
import com.billcom.eshop.InterfaceService.InterfaceNumService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SecurityRequirement(name = "bearer-key")
@CrossOrigin("*")
@RestController
@RequestMapping("/api/Num")
public class NumController {

    @Autowired

    private InterfaceNumService numService;

    @PostMapping("/acheterSim")
    public ResponseEntity<NumResponse> acheterSim(@RequestParam Long utilisateurId, @RequestBody NumRequestAcheterSim numRequest) {
        return numService.acheterSim(utilisateurId, numRequest);
    }

    @PostMapping("/acheterEsim")
    public ResponseEntity<NumResponse> acheterEsim(@RequestParam Long utilisateurId, @RequestBody NumRequest numRequest) {
        return numService.acheterEsim(utilisateurId, numRequest);
    }

    @PostMapping("/changerSimVersEsim")
    public ResponseEntity<NumResponse> changerSimVersEsim(@RequestParam Long numPhoneNumber, @RequestParam Long numSerialNumber, @RequestParam String numImei, @RequestParam Long phoneTypeId) {
        return numService.changerSimVersEsim(numPhoneNumber, numSerialNumber, numImei, phoneTypeId);
    }

    @PostMapping("/changerEsimVersSim")
    public ResponseEntity<NumResponse> changerEsimVersSim(@RequestParam Long numPhoneNumber, @RequestParam String numImei) {
        return numService.changerEsimVersSim(numPhoneNumber, numImei);
    }

    @GetMapping("/findAllNum")
    public NumResponse findAllNum() {
        return numService.findAllNum();
    }

    @GetMapping("/findAllNumById")
    public NumResponse findAllNumById(@RequestParam Long utilisateurId) {
        return numService.findAllNumById(utilisateurId);
    }

}


