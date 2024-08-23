package com.billcom.eshop.controller;

import com.billcom.eshop.Request.ClaimRequest;
import com.billcom.eshop.Request.ClaimRequestReponce;
import com.billcom.eshop.Responce.ClaimResponse;
import com.billcom.eshop.commons.entities.Claim;
import com.billcom.eshop.InterfaceService.InterfaceClaimService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin("*")
@SecurityRequirement(name = "bearer-key")
@RequestMapping("/api/claim")
public class ClaimContoller {
    @Autowired
    private InterfaceClaimService claimService;

    //    @PostMapping("ajouterClaim")
   //     public Claim addClaim(@RequestBody Claim claim) {
     //   return claimService.addClaim(claim);
   //     }

    @PostMapping("ajouterReclamation")
    public ClaimResponse addClaim(@RequestBody ClaimRequest claimRequest) {
        return claimService.addClaim(claimRequest);
    }

   //    @DeleteMapping("supprimerReclamationId")
   //    public void deleteClaim(@RequestParam  long clId) {

     //  claimService.deleteClaim(clId);
   //    }

     @DeleteMapping("supprimerReclamationId")
     public ClaimResponse deleteClaim(@RequestParam long clId) {
         return claimService.deleteClaim(clId);
     }



   //     @GetMapping("afficherListReclamation")
   //     public List<Claim> findAll() {

   //     return claimService.findAllClaim();
   //     }

    @GetMapping("afficherListReclamation")
    public ClaimResponse findAll() {
        return  claimService.findAllClaim();
    }


   //     @GetMapping("afficherReclamationId")
    //    public Claim findClaimById(@RequestParam long clId) {
      //  return claimService.findClaimById(clId);
  //      }





    @PutMapping("modifierReclamationId")
    public ClaimResponse updateClaim(@RequestParam long clId, @RequestBody ClaimRequest claimRequest) {
        return claimService.updateClaim(clId, claimRequest);
    }




    @PutMapping("/repondreReclamation/{id}")
    public ResponseEntity<ClaimResponse> reponseClaim(@PathVariable Long id, @RequestBody ClaimRequestReponce claimRequestReponce) {
        ClaimResponse claimResponse = claimService.reponseClaim(id, claimRequestReponce);

        if (claimResponse.getIsSuccessfull()) {
            return ResponseEntity.ok(claimResponse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(claimResponse);
        }
    }




}
