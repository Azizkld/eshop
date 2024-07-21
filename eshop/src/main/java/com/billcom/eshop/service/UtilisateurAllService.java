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
            utilisateurAll.setUtAdresse(request.getUtAdresse());
            utilisateurAll.setUtCity(request.getUtCity());
            utilisateurAll.setUtCountry(request.getUtCountry());
            utilisateurAll.setUtFName(request.getUtFName());
            utilisateurAll.setUtLName(request.getUtLName());
            utilisateurAll.setUtMail(request.getUtMail());
            utilisateurAll.setUtZipCode(request.getUtZipCode());

            utilisateurAllRepository.save(utilisateurAll);
            response.setSuccessfull(true);
            response.setUtilisateurAll(utilisateurAll);
        } else {
            response.setSuccessfull(false);
            response.setMessage("Utilisateur not found with id: " + id);
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

