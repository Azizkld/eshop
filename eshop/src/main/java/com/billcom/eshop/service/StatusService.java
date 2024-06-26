package com.billcom.eshop.service;

import com.billcom.eshop.InterfaceService.InterfaceStatusService;
import com.billcom.eshop.Request.StatusRequest;
import com.billcom.eshop.Responce.StatusResponse;
import com.billcom.eshop.commons.entities.UtilisateurAll;
import com.billcom.eshop.commons.repositories.UtilisateurAllRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StatusService implements InterfaceStatusService {
    @Autowired
    private UtilisateurAllRepository utilisateurAllRepository;

    @Override
    public StatusResponse changeUserStatus(StatusRequest statusRequest) {
        StatusResponse statusResponse = new StatusResponse();
        Optional<UtilisateurAll> utilisateurOptional = utilisateurAllRepository.findById(statusRequest.getUserId());

        if (utilisateurOptional.isPresent()) {
            UtilisateurAll utilisateur = utilisateurOptional.get();
            utilisateur.setUtStatus(true);  // Changer le statut à true (1)
            utilisateurAllRepository.save(utilisateur);
            statusResponse.setSuccess(true);
            statusResponse.setMessage("User status updated successfully.");
        } else {
            statusResponse.setSuccess(false);
            statusResponse.setMessage("User not found.");
        }

        return statusResponse;
    }
}
