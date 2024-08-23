package com.billcom.eshop.service;

import com.billcom.eshop.InterfaceService.InterfaceNumAjoutService;
import com.billcom.eshop.Request.NumAjoutRequest;
import com.billcom.eshop.Responce.NumAjoutResponse;
import com.billcom.eshop.commons.entities.Num;
import com.billcom.eshop.commons.repositories.NumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NumAjoutService implements InterfaceNumAjoutService {

    @Autowired
    private NumRepository numRepository;

    @Override
    public NumAjoutResponse ajouterNumero(NumAjoutRequest numAjoutRequest) {
        NumAjoutResponse numAjoutResponse = new NumAjoutResponse();
        try {
            // Vérifier si le numéro de téléphone existe déjà
            if (numRepository.existsByNumPhoneNumber(numAjoutRequest.getNumPhoneNumber())) {
                throw new IllegalArgumentException("Le numéro de téléphone est déjà utilisé.");
            }

            // Validation des critères pour numPinCode, numPukCode et numPhoneNumber
            if (!isValidPinCode(numAjoutRequest.getNumPinCode()) ||
                    !isValidPukCode(numAjoutRequest.getNumPukCode()) ||
                    !isValidPhoneNumber(numAjoutRequest.getNumPhoneNumber())) {
                throw new IllegalArgumentException("Les valeurs pour numPinCode, numPukCode ou numPhoneNumber ne sont pas valides.");
            }

            Num num = new Num();
            num.setNumOperatorName(numAjoutRequest.getNumOperatorName());
            num.setNumPinCode(numAjoutRequest.getNumPinCode());
            num.setNumPukCode(numAjoutRequest.getNumPukCode());
            num.setNumActivationStatus(false); // Status d'activation défini sur false (0)
            num.setNumPhoneNumber(numAjoutRequest.getNumPhoneNumber());
            num.setNumType("unknown"); // assuming the type to be unknown or setting it later
            num.setNumActivationDate(null);
            num.setNumSerialNumber(null);
            num.setNumImei(null);
            num.setUtilisateurAll(null);
            num.setPhoneType(null);

            Num savedNum = numRepository.save(num);
            numAjoutResponse.setIsSuccessfull(true);
            numAjoutResponse.setNum(savedNum);
            numAjoutResponse.setMessage("Numéro ajouté avec succès");
        } catch (Exception e) {
            numAjoutResponse.setIsSuccessfull(false);
            numAjoutResponse.setMessage("Erreur lors de l'ajout du numéro: " + e.getMessage());
        }
        return numAjoutResponse;
    }

    private boolean isValidPinCode(Long pinCode) {
        String pinCodeStr = String.format("%04d", pinCode);
        return pinCodeStr.length() == 4;
    }

    private boolean isValidPukCode(Long pukCode) {
        String pukCodeStr = String.format("%04d", pukCode);
        return pukCodeStr.length() == 4;
    }

    private boolean isValidPhoneNumber(Long phoneNumber) {
        String phoneNumberStr = String.valueOf(phoneNumber);
        return phoneNumberStr.matches("^5\\d{7}$");
    }
}

