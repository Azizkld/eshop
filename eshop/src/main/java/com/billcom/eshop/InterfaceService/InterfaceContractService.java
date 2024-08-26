package com.billcom.eshop.InterfaceService;

import com.billcom.eshop.Request.ContractRequest;
import com.billcom.eshop.Request.ContractRequestAjouter;
import com.billcom.eshop.Responce.ContractResponse;

public interface InterfaceContractService {
    ContractResponse ajouterContract(ContractRequestAjouter contractRequestAjouter);
    ContractResponse findAllContract();
    ContractResponse findAllContractById(Long utilisateurId);
    ContractResponse supprimerContract(Long contractId);

}
