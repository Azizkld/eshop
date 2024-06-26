package com.billcom.eshop.InterfaceService;

import com.billcom.eshop.Request.NumRequest;
import com.billcom.eshop.Responce.NumResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface InterfaceNumService {

    ResponseEntity<NumResponse> acheterSim(Long utilisateurId, NumRequest numRequest);
    ResponseEntity<NumResponse> acheterEsim(Long utilisateurId, NumRequest numRequest);
    ResponseEntity<NumResponse> changerSimVersEsim(Long numPhoneNumber, Long numSerialNumber, String numImei, Long phoneTypeId);
    ResponseEntity<NumResponse> changerEsimVersSim(Long numPhoneNumber, String numImei);
    NumResponse findAllNum();
    NumResponse findAllNumById(Long utilisateurId);

}
