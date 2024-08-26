package com.billcom.eshop.commons.repositories;

import com.billcom.eshop.commons.entities.Num;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NumRepository extends JpaRepository<Num, Long> {
    Optional<Num> findFirstByUtilisateurAllIsNull();
    Optional<Num> findByNumPhoneNumberAndNumSerialNumberAndNumType(Long numPhoneNumber, Long numSerialNumber, String numType);

    Optional<Num>  findByNumPhoneNumberAndNumType(Long phoneNumber, String numPhoneNumber);

    List<Num> findAllByNumActivationStatusTrueAndUtilisateurAllIsNotNull();

    List<Num> findAllByUtilisateurAllId(Long utilisateurId);

    boolean existsByNumPhoneNumber(Long numPhoneNumber);
    Optional<Num> findByNumPhoneNumber(Long numPhoneNumber);
}