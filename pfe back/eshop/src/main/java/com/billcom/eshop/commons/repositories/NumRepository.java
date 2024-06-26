package com.billcom.eshop.commons.repositories;

import com.billcom.eshop.commons.entities.Num;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NumRepository extends JpaRepository<Num, Long> {
}