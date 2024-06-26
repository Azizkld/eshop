package com.billcom.eshop.commons.repositories;

import com.billcom.eshop.commons.entities.UtilisateurAll;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtilisateurAllRepository extends JpaRepository<UtilisateurAll, Long> {
    Optional<UtilisateurAll> findByUtCin(String cin);
}