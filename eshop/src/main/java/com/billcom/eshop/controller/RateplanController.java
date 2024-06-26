package com.billcom.eshop.controller;

import com.billcom.eshop.Request.RateplanRequest;
import com.billcom.eshop.Responce.RateplanResponse;
import com.billcom.eshop.InterfaceService.InterfaceRateplanService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@SecurityRequirement(name = "bearer-key")
@CrossOrigin("*")
@RestController
@RequestMapping("/api/RatePlan")
public class RateplanController {

    @Autowired
    private InterfaceRateplanService rateplanService;

    @PostMapping("/ajouterOffre")
    public RateplanResponse addRateplan(@RequestBody RateplanRequest rateplanRequest) {
        return rateplanService.addRateplan(rateplanRequest);
    }

    @DeleteMapping("/supprimerOffre/{id}")
    public RateplanResponse deleteRateplan(@PathVariable Long id) {
        return rateplanService.deleteRateplan(id);
    }

    @GetMapping("/afficherTousOffres")
    public RateplanResponse findAllRateplans() {
        return rateplanService.findAllRateplans();
    }

    @GetMapping("/afficherOffre/{id}")
    public RateplanResponse findRateplanById(@PathVariable Long id) {
        return rateplanService.findRateplanById(id);
    }

    @PutMapping("/modifierOffre/{id}")
    public RateplanResponse updateRateplan(@PathVariable Long id, @RequestBody RateplanRequest rateplanRequest) {
        return rateplanService.updateRateplan(id, rateplanRequest);
    }

}
