package com.billcom.eshop.commons.repositories;

import com.billcom.eshop.commons.entities.Esim;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EsimRepository extends JpaRepository<Esim, Long> {
}