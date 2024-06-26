package com.billcom.eshop.commons.repositories;

import com.billcom.eshop.commons.entities.Claim;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClaimRepository extends JpaRepository<Claim, Long> {
}