package com.billcom.eshop.service;

import com.billcom.eshop.InterfaceService.EmailService;
import com.billcom.eshop.InterfaceService.InterfaceClaimService;
import com.billcom.eshop.Request.ClaimRequestReponce;
import com.billcom.eshop.Request.EmailDetails;
import com.billcom.eshop.commons.entities.Claim;
import com.billcom.eshop.commons.entities.UtilisateurAll;
import com.billcom.eshop.commons.mappers.ClaimMapper;
import com.billcom.eshop.commons.repositories.ClaimRepository;
import com.billcom.eshop.Request.ClaimRequest;
import com.billcom.eshop.Responce.ClaimResponse;
import com.billcom.eshop.commons.repositories.UtilisateurAllRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClaimService implements InterfaceClaimService {

    @Autowired
    ClaimRepository claimRepository;
    @Autowired
    ClaimMapper claimMapper;

    @Autowired
    private UtilisateurAllRepository utilisateurRepository;

    @Autowired
    private EmailService emailService; // Injecter le service d'email



   //     @Override
    //    public Claim addClaim(Claim claim) {
      //  return claimRepository.save(Claim);
   //     }
   @Override
   public ClaimResponse addClaim(ClaimRequest claimRequest) {
       ClaimResponse claimResponse = new ClaimResponse();

       // Récupérer l'utilisateur par ID
       Optional<UtilisateurAll> utilisateurOptional = utilisateurRepository.findById(claimRequest.getUtilisateurId());
       if (utilisateurOptional.isPresent()) {
           UtilisateurAll utilisateur = utilisateurOptional.get();

           // Créer un nouveau claim
           Claim claim = new Claim();
           claim.setClDesc(claimRequest.getDesc());
           claim.setClReponce(""); // Réponse vide à la création
           claim.setClStatus(false); // Statut initial à false (non résolu)
           claim.setClDate(LocalDate.now()); // Date actuelle
           claim.setUtilisateurAll(utilisateur); // Associer l'utilisateur au claim

           // Sauvegarder le claim
           claimRepository.save(claim);

           claimResponse.setIsSuccessfull(true);
           claimResponse.setClaim(claim);
       } else {
           claimResponse.setIsSuccessfull(false);
           claimResponse.setMessage("Utilisateur not found with id: " + claimRequest.getUtilisateurId());
       }

       return claimResponse;
   }

    //    @Override
   //     public void deleteClaim(Long id) {
      //  claimRepository.deleteById(id);
   //     }

    @Override
    public ClaimResponse deleteClaim(Long id) {
        ClaimResponse claimResponse = new ClaimResponse();
        if (claimRepository.existsById(id)) {
            claimRepository.deleteById(id);
            claimResponse.setIsSuccessfull(true);
        } else {
            claimResponse.setIsSuccessfull(false);
            claimResponse.setMessage("Claim not found with id: " + id);
        }
        return claimResponse;
    }


   //      @Override
   //      public List<Claim> findAllClaim() {
   //      return claimRepository.findAll();
   //      }

    @Override
    public ClaimResponse findAllClaim() {
        // Récupérer toutes les réclamations et filtrer celles où clStatus est faux (0)
        List<Claim> claims = claimRepository.findAll().stream()
                .filter(claim -> !claim.isClStatus())
                .collect(Collectors.toList());

        ClaimResponse claimResponse = new ClaimResponse();

        if (claims.isEmpty()) {
            claimResponse.setIsSuccessfull(false);
            claimResponse.setMessage("No claims found");
        } else {
            claimResponse.setIsSuccessfull(true);
            // Map each claim to include additional user details
            List<Map<String, Object>> claimList = claims.stream().map(claim -> {
                Map<String, Object> claimMap = new HashMap<>();
                claimMap.put("id", claim.getClid());
                claimMap.put("description", claim.getClDesc());
                claimMap.put("response", claim.getClReponce());
                claimMap.put("status", claim.isClStatus());
                claimMap.put("date", claim.getClDate());

                // Add user details
                Map<String, Object> userMap = new HashMap<>();
                userMap.put("id", claim.getUtilisateurAll().getId());
                userMap.put("firstName", claim.getUtilisateurAll().getUtFName());
                userMap.put("cin" , claim.getUtilisateurAll().getUtCin());
                userMap.put("lastName", claim.getUtilisateurAll().getUtLName());
                userMap.put("email", claim.getUtilisateurAll().getUtMail());
                claimMap.put("user", userMap);

                return claimMap;
            }).collect(Collectors.toList());

            claimResponse.setClaims(claimList);
        }

        return claimResponse;
    }


    //     @Override
   //     public Claim findClaimById(Long id) {
   //     return claimRepository.findById(id).get();
   //      }





    @Override
    public ClaimResponse updateClaim(Long id, ClaimRequest claimRequest) {
        Optional<Claim> claimOptional = claimRepository.findById(id);
        ClaimResponse claimResponse = new ClaimResponse();
        if (claimOptional.isPresent()) {
            Claim claim = claimOptional.get();
            claim.setClDesc(claimRequest.getDesc()); // Mettre à jour les autres champs nécessaires
            claimRepository.save(claim);
            claimResponse.setIsSuccessfull(true);
            claimResponse.setClaim(claim);
        } else {
            claimResponse.setIsSuccessfull(false);
            claimResponse.setMessage("Claim not found with id: " + id);
        }
        return claimResponse;
    }
    @Override
    public ClaimResponse reponseClaim(Long id, ClaimRequestReponce claimRequestReponce) {
        ClaimResponse claimResponse = new ClaimResponse();
        Optional<Claim> claimOptional = claimRepository.findById(id);

        if (claimOptional.isPresent()) {
            Claim claim = claimOptional.get();
            claim.setClReponce(claimRequestReponce.getClReponce()); // Mettre à jour la réponse
            claim.setClStatus(true); // Mettre à jour le statut à 1 (true)
            claimRepository.save(claim);

            // Envoyer un email à l'utilisateur pour l'informer de la réponse à sa réclamation
            UtilisateurAll utilisateur = claim.getUtilisateurAll();
            String emailBody = "Bonjour " + utilisateur.getUtFName() + " " + utilisateur.getUtLName() + ",\n\n" +
                    "Nous avons répondu à votre réclamation : \n\n" +
                    "Description : " + claim.getClDesc() + "\n" +
                    "Réponse : " + claim.getClReponce() + "\n\n" +
                    "Merci pour votre patience.\n\nCordialement,\nVotre service client";

            EmailDetails emailDetails = new EmailDetails(
                    utilisateur.getUtMail(),
                    emailBody,
                    "Réponse à votre réclamation",
                    null
            );

            emailService.sendSimpleMail(emailDetails);

            claimResponse.setIsSuccessfull(true);
            claimResponse.setClaim(claim);
        } else {
            claimResponse.setIsSuccessfull(false);
            claimResponse.setMessage("Claim not found with id: " + id);
        }

        return claimResponse;
    }



}
