package com.billcom.eshop.Responce;

import com.billcom.eshop.commons.entities.ContractAll;
import lombok.Data;

import java.util.List;

@Data
public class ContractResponse  {
    private Boolean isSuccessfull;
    private String message;
    private ContractAll contract;
    private List<ContractAll> contracts;
}
