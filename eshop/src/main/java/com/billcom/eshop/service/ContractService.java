package com.billcom.eshop.service;

import com.billcom.eshop.InterfaceService.EmailService;
import com.billcom.eshop.InterfaceService.InterfaceContractService;
import com.billcom.eshop.Request.ContractRequest;
import com.billcom.eshop.Request.EmailDetails;
import com.billcom.eshop.Responce.ContractResponse;
import com.billcom.eshop.commons.entities.ContractAll;
import com.billcom.eshop.commons.entities.Num;
import com.billcom.eshop.commons.entities.Rateplan;
import com.billcom.eshop.commons.entities.UtilisateurAll;
import com.billcom.eshop.commons.repositories.ContractAllRepository;
import com.billcom.eshop.commons.repositories.NumRepository;
import com.billcom.eshop.commons.repositories.RateplanRepository;
import com.billcom.eshop.commons.repositories.UtilisateurAllRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class ContractService implements InterfaceContractService {

    @Autowired
    private ContractAllRepository contractRepository;

    @Autowired
    private UtilisateurAllRepository utilisateurRepository;

    @Autowired
    private NumRepository numRepository;

    @Autowired
    private RateplanRepository rateplanRepository;
    @Autowired
    private EmailService emailService;
    @Override
    public ContractResponse ajouterContract(ContractRequest contractRequest) {
       ContractResponse contractResponse = new ContractResponse();

        // Fetch utilisateur, num, and rateplan from database
        Optional<UtilisateurAll> utilisateurOptional = utilisateurRepository.findById(contractRequest.getUtilisateurId());
        Optional<Num> numOptional = numRepository.findById(contractRequest.getNumId());
        Optional<Rateplan> rateplanOptional = rateplanRepository.findById(contractRequest.getRateplanId());

        if (utilisateurOptional.isPresent() && numOptional.isPresent() && rateplanOptional.isPresent()) {
            UtilisateurAll utilisateur = utilisateurOptional.get();
            Num num = numOptional.get();
            Rateplan rateplan = rateplanOptional.get();

            // Ensure the num belongs to the utilisateur
            if (!num.getUtilisateurAll().getId().equals(utilisateur.getId())) {
                contractResponse.setIsSuccessfull(false);
                contractResponse.setMessage("Num does not belong to the utilisateur.");
                return contractResponse;
            }

            // Create a new contract
            ContractAll newContract = new ContractAll();
            newContract.setUtilisateurAll(utilisateur);
            newContract.setNum(num);
            newContract.setRateplan(rateplan);

            LocalDate currentDate = LocalDate.now();
            newContract.setCoActivDate(currentDate);
            newContract.setCoExpirDate(currentDate.plusDays(rateplan.getRpValidationDays()));
            newContract.setCoStatus(true);
            newContract.setCoCode(generateRandomCoCode());


            // Save the new contract
            contractRepository.save(newContract);

            contractResponse.setIsSuccessfull(true);
            contractResponse.setContract(newContract);

            //envois mail notification
            EmailDetails emailDetails = new EmailDetails(utilisateur.getUtMail(),"Nous avons le plaisir de vous informer que votre contrat a été créé avec succès","contract","");
            emailService.sendSimpleMail(emailDetails);

        } else {
            contractResponse.setIsSuccessfull(false);
            contractResponse.setMessage("Invalid utilisateur, num or rateplan ID.");

        }




        return contractResponse;
    }

    private Long generateRandomCoCode() {
        Random random = new Random();
        return 1000000000L + random.nextLong(9000000000L);
    }

    private String generateRandomQrCode() {
        int length = 6;
        StringBuilder qrCode = new StringBuilder("QR");
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            qrCode.append(random.nextInt(10));
        }
        return qrCode.toString();
    }

    @Override
    public ContractResponse findAllContract() {
        ContractResponse contractResponse = new ContractResponse();
        List<ContractAll> contracts = contractRepository.findAll();
        if (contracts.isEmpty()) {
            contractResponse.setIsSuccessfull(false);
            contractResponse.setMessage("No contracts found");
        } else {
            contractResponse.setIsSuccessfull(true);
            contractResponse.setContracts(contracts);
        }
        return contractResponse;
    }

    @Override
    public ContractResponse findAllContractById(Long utilisateurId) {
        ContractResponse contractResponse = new ContractResponse();
        Optional<UtilisateurAll> utilisateurOptional = utilisateurRepository.findById(utilisateurId);
        if (utilisateurOptional.isPresent()) {
            List<ContractAll> contracts = contractRepository.findByUtilisateurAllId(utilisateurId);
            if (contracts.isEmpty()) {
                contractResponse.setIsSuccessfull(false);
                contractResponse.setMessage("No contracts found for utilisateur id: " + utilisateurId);
            } else {
                contractResponse.setIsSuccessfull(true);
                contractResponse.setContracts(contracts);
            }
        } else {
            contractResponse.setIsSuccessfull(false);
            contractResponse.setMessage("Utilisateur not found with id: " + utilisateurId);
        }
        return contractResponse;
    }
}
