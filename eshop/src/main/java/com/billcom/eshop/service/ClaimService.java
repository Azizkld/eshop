package com.billcom.eshop.service;

import com.billcom.eshop.InterfaceService.InterfaceClaimService;
import com.billcom.eshop.commons.dtos.ClaimDto;
import com.billcom.eshop.commons.entities.Claim;
import com.billcom.eshop.commons.mappers.ClaimMapper;
import com.billcom.eshop.commons.repositories.ClaimRepository;
import com.billcom.eshop.Request.ClaimRequest;
import com.billcom.eshop.Responce.ClaimResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClaimService implements InterfaceClaimService {

    @Autowired
    ClaimRepository claimRepository;
    @Autowired
    ClaimMapper claimMapper;



   //     @Override
    //    public Claim addClaim(Claim claim) {
      //  return claimRepository.save(Claim);
   //     }
    @Override
    public ClaimResponse addClaim(ClaimRequest claimRequest){
        ClaimDto claimDto = ClaimDto.builder()
                .clDesc(claimRequest.getDesc())
                .build();
        Claim claim = this.claimMapper.toEntity(claimDto);
         this.claimRepository.save(claim);
        ClaimResponse claimResponse = new ClaimResponse();
       claimResponse.setIsSuccessfull(true);
       return claimResponse;
    }

    //    @Override
   //     public void deleteClaim(Long id) {
      //  claimRepository.deleteById(id);
   //     }

    @Override
    public ClaimResponse deleteClaim(Long id) {
        ClaimResponse claimResponse = new ClaimResponse();
        if (claimRepository.existsById(id)) {
            claimRepository.deleteById(id);
            claimResponse.setIsSuccessfull(true);
        } else {
            claimResponse.setIsSuccessfull(false);
            claimResponse.setMessage("Claim not found with id: " + id);
        }
        return claimResponse;
    }


   //      @Override
   //      public List<Claim> findAllClaim() {
   //      return claimRepository.findAll();
   //      }

    @Override
    public ClaimResponse findAllClaim() {
        List<Claim> claims = claimRepository.findAll();
        ClaimResponse claimResponse = new ClaimResponse();
        if (claims.isEmpty()) {
            claimResponse.setIsSuccessfull(false);
            claimResponse.setMessage("No claims found");
        } else {
            claimResponse.setIsSuccessfull(true);
            claimResponse.setClaims(claims);
        }
        return  claimResponse;
    }

   //     @Override
   //     public Claim findClaimById(Long id) {
   //     return claimRepository.findById(id).get();
   //      }

    @Override
    public Claim findClaimById(Long id) {
        Optional<Claim> claimOptional = claimRepository.findById(id);
        ClaimResponse claimResponse = new ClaimResponse();
        if (claimOptional.isPresent()) {
            claimResponse.setIsSuccessfull(true);
            claimResponse.setClaim(claimOptional.get());
        } else {
            claimResponse.setIsSuccessfull(false);
            claimResponse.setMessage("Claim not found with id: " + id);
        }
        return claimResponse.getClaim();
    }


    @Override
    public ClaimResponse updateClaim(Long id, ClaimRequest claimRequest) {
        Optional<Claim> claimOptional = claimRepository.findById(id);
        ClaimResponse claimResponse = new ClaimResponse();
        if (claimOptional.isPresent()) {
            Claim claim = claimOptional.get();
            claim.setClDesc(claimRequest.getDesc()); // Mettre à jour les autres champs nécessaires
            claimRepository.save(claim);
            claimResponse.setIsSuccessfull(true);
            claimResponse.setClaim(claim);
        } else {
            claimResponse.setIsSuccessfull(false);
            claimResponse.setMessage("Claim not found with id: " + id);
        }
        return claimResponse;
    }


}
