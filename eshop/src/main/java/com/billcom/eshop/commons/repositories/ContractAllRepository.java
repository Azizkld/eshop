package com.billcom.eshop.commons.repositories;

import com.billcom.eshop.commons.entities.ContractAll;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContractAllRepository extends JpaRepository<ContractAll, Long> {
    List<ContractAll> findByUtilisateurAllId(Long utilisateurId);

}