package com.billcom.eshop.commons.repositories;

import com.billcom.eshop.commons.entities.PhoneType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PhoneTypeRepository extends JpaRepository<PhoneType, Long> {

}