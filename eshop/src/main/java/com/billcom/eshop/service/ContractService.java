package com.billcom.eshop.service;

import com.billcom.eshop.InterfaceService.EmailService;
import com.billcom.eshop.InterfaceService.InterfaceContractService;
import com.billcom.eshop.Request.ContractRequest;
import com.billcom.eshop.Request.ContractRequestAjouter;
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
    public ContractResponse ajouterContract(ContractRequestAjouter contractRequestAjouter) {
        ContractResponse contractResponse = new ContractResponse();

        // Fetch utilisateur, num (by phone number), and rateplan from database
        Optional<UtilisateurAll> utilisateurOptional = utilisateurRepository.findById(contractRequestAjouter.getUtilisateurId());
        Optional<Num> numOptional = numRepository.findByNumPhoneNumber(contractRequestAjouter.getNumPhoneNumber());
        Optional<Rateplan> rateplanOptional = rateplanRepository.findById(contractRequestAjouter.getRateplanId());

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

            // Send email notification
            EmailDetails emailDetails = new EmailDetails(utilisateur.getUtMail(),
                    "Nous avons le plaisir de vous informer que votre contrat a été créé avec succès",
                    "contract",
                    "");
            emailService.sendSimpleMail(emailDetails);

        } else {
            contractResponse.setIsSuccessfull(false);
            contractResponse.setMessage("Invalid utilisateur, num, or rateplan ID.");
        }

        return contractResponse;
    }

    private Long generateRandomCoCode() {
        Random random = new Random();
        return 1000000000L + random.nextLong(9000000000L);
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

        // Recherche de tous les contrats associés à l'ID de l'utilisateur
        List<ContractAll> contracts = contractRepository.findByUtilisateurAllId(utilisateurId);
        if (contracts.isEmpty()) {
            contractResponse.setIsSuccessfull(false);
            contractResponse.setMessage("Aucun contrat trouvé pour l'utilisateur avec l'ID : " + utilisateurId);
        } else {
            contractResponse.setIsSuccessfull(true);
            contractResponse.setContracts(contracts);
        }

        return contractResponse;
    }

    @Override
    public ContractResponse supprimerContract(Long contractId) {
        ContractResponse contractResponse = new ContractResponse();
        Optional<ContractAll> contractOptional = contractRepository.findById(contractId);

        if (contractOptional.isPresent()) {
            contractRepository.deleteById(contractId);
            contractResponse.setIsSuccessfull(true);
            contractResponse.setMessage("Contract successfully deleted.");
        } else {
            contractResponse.setIsSuccessfull(false);
            contractResponse.setMessage("Contract not found with id: " + contractId);
        }

        return contractResponse;
    }
}
