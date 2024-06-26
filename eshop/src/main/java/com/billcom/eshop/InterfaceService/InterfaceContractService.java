package com.billcom.eshop.InterfaceService;

import com.billcom.eshop.Request.ContractRequest;
import com.billcom.eshop.Responce.ContractResponse;

public interface InterfaceContractService {
    ContractResponse ajouterContract(ContractRequest contractRequest);
    ContractResponse findAllContract();
    ContractResponse findAllContractById(Long utilisateurId);

}
