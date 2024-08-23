package com.billcom.eshop.service;

import com.billcom.eshop.InterfaceService.InterfaceUtilisateurAllService;
import com.billcom.eshop.Request.UtilisateurAllRequest;
import com.billcom.eshop.Responce.UtilisateurAllResponse;
import com.billcom.eshop.commons.entities.UtilisateurAll;
import com.billcom.eshop.commons.mappers.UtilisateurAllMapper;
import com.billcom.eshop.commons.repositories.UtilisateurAllRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurAllService implements InterfaceUtilisateurAllService {

    @Autowired
    private UtilisateurAllRepository utilisateurAllRepository;

    @Autowired
    private UtilisateurAllMapper utilisateurAllMapper;

    @Override
    public UtilisateurAllResponse deleteUtilisateur(Long id) {
        UtilisateurAllResponse response = new UtilisateurAllResponse();
        if (utilisateurAllRepository.existsById(id)) {
            utilisateurAllRepository.deleteById(id);
            response.setSuccessfull(true);
        } else {
            response.setSuccessfull(false);
            response.setMessage("Utilisateur not found with id: " + id);
        }
        return response;
    }

    @Override
    public UtilisateurAllResponse findAllUtilisateurs() {
        List<UtilisateurAll> utilisateurs = utilisateurAllRepository.findAll();
        UtilisateurAllResponse response = new UtilisateurAllResponse();
        if (utilisateurs.isEmpty()) {
            response.setSuccessfull(false);
            response.setMessage("No utilisateurs found");
        } else {
            response.setSuccessfull(true);
            response.setUtilisateursAlls(utilisateurs);        }
        return response;
    }

    @Override
    public UtilisateurAllResponse findUtilisateurById(Long id) {
        Optional<UtilisateurAll> utilisateurOptional = utilisateurAllRepository.findById(id);
        UtilisateurAllResponse response = new UtilisateurAllResponse();
        if (utilisateurOptional.isPresent()) {
            response.setSuccessfull(true);
            response.setUtilisateurAll(utilisateurOptional.get());
        } else {
            response.setSuccessfull(false);
            response.setMessage("Utilisateur not found with id: " + id);
        }
        return response;
    }

    @Override
    public UtilisateurAllResponse updateUtilisateur(Long id, UtilisateurAllRequest request) {
        Optional<UtilisateurAll> utilisateurOptional = utilisateurAllRepository.findById(id);
        UtilisateurAllResponse response = new UtilisateurAllResponse();

        if (utilisateurOptional.isPresent()) {
            UtilisateurAll utilisateurAll = utilisateurOptional.get();

            // Vérifier le mot de passe actuel
            if (!utilisateurAll.getUtPassword().equals(request.getCurrentPassword())) {
                response.setSuccessfull(false);
                response.setMessage("Mot de passe actuel incorrect.");
                return response;
            }

            // Ne pas permettre la modification de ces champs
            // utilisateurAll.setUtCin(request.getUtCin()); // Non modifiable
            // utilisateurAll.setUtFName(request.getUtFName()); // Non modifiable
            // utilisateurAll.setUtLName(request.getUtLName()); // Non modifiable

            // Mise à jour des champs optionnels
            if (request.getUtAdresse() != null) {
                utilisateurAll.setUtAdresse(request.getUtAdresse());
            }
            if (request.getUtCity() != null) {
                utilisateurAll.setUtCity(request.getUtCity());
            }
            if (request.getUtCountry() != null) {
                utilisateurAll.setUtCountry(request.getUtCountry());
            }
            if (request.getUtZipCode() != null) {
                utilisateurAll.setUtZipCode(request.getUtZipCode());
            }

            // Mise à jour des champs obligatoires qui ne doivent pas être vides
            if (request.getUtMail() != null && !request.getUtMail().isEmpty()) {
                utilisateurAll.setUtMail(request.getUtMail());
            } else {
                response.setSuccessfull(false);
                response.setMessage("L'email ne peut pas être vide.");
                return response;
            }

            if (request.getUtPassword() != null && !request.getUtPassword().isEmpty()) {
                utilisateurAll.setUtPassword(request.getUtPassword());
            } else {
                response.setSuccessfull(false);
                response.setMessage("Le mot de passe ne peut pas être vide.");
                return response;
            }

            // Sauvegarder les modifications
            utilisateurAllRepository.save(utilisateurAll);
            response.setSuccessfull(true);
            response.setUtilisateurAll(utilisateurAll);
        } else {
            response.setSuccessfull(false);
            response.setMessage("Utilisateur non trouvé avec l'ID: " + id);
        }
        return response;
    }


    @Override
    public UtilisateurAll UtilisateurUpdateImage(Long id, String imageName) {
        UtilisateurAll utilisateur = utilisateurAllRepository.findById(id).get();
        utilisateur.setUtImage(imageName);
        System.out.println("done");

        return utilisateurAllRepository.save(utilisateur);
    }
}

