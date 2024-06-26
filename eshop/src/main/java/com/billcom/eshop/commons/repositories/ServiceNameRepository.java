package com.billcom.eshop.commons.repositories;

import com.billcom.eshop.commons.entities.ServiceName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceNameRepository extends JpaRepository<ServiceName, Long> {
}