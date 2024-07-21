package com.billcom.eshop.service;

import com.billcom.eshop.InterfaceService.InterfaceStatusService;
import com.billcom.eshop.InterfaceService.EmailService;
import com.billcom.eshop.Request.StatusRequest;
import com.billcom.eshop.Request.EmailDetails;
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

    @Autowired
    private EmailService emailService; // Ajout du service EmailService

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

            // Envoi de l'email de notification
            EmailDetails emailDetails = new EmailDetails(
                    utilisateur.getUtMail(),
                    "Votre compte a ete verifier par notre administarateur tu a l acce dans notre application(Orange Plus)",
                    "Notification du votre validation du compte",
                    ""
            );
            emailService.sendSimpleMail(emailDetails);

        } else {
            statusResponse.setSuccess(false);
            statusResponse.setMessage("User not found.");
        }

        return statusResponse;
    }
}

