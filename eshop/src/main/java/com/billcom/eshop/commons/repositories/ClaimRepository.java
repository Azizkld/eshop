package com.billcom.eshop.commons.repositories;

import com.billcom.eshop.commons.entities.Claim;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClaimRepository extends JpaRepository<Claim, Long> {

}