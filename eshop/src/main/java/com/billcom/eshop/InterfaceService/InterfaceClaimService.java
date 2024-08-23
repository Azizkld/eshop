package com.billcom.eshop.InterfaceService;

import com.billcom.eshop.Request.ClaimRequest;
import com.billcom.eshop.Request.ClaimRequestReponce;
import com.billcom.eshop.Responce.ClaimResponse;

public interface InterfaceClaimService {

    ClaimResponse addClaim(ClaimRequest claim);

ClaimResponse deleteClaim(Long id);


    ClaimResponse findAllClaim();

    ClaimResponse reponseClaim(Long id, ClaimRequestReponce claimRequestReponce);

    ClaimResponse updateClaim(Long id, ClaimRequest claimRequest);


}
