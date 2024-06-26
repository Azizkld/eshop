package com.billcom.eshop.InterfaceService;

import com.billcom.eshop.commons.entities.Claim;
import com.billcom.eshop.Request.ClaimRequest;
import com.billcom.eshop.Responce.ClaimResponse;

public interface InterfaceClaimService {

    ClaimResponse addClaim(ClaimRequest claim);

ClaimResponse deleteClaim(Long id);


    ClaimResponse findAllClaim();

Claim findClaimById(Long id);


    ClaimResponse updateClaim(Long id, ClaimRequest claimRequest);
}
