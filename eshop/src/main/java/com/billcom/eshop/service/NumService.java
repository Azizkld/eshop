package com.billcom.eshop.service;

import com.billcom.eshop.InterfaceService.InterfaceNumService;
import com.billcom.eshop.Request.NumRequest;
import com.billcom.eshop.Request.NumRequestAcheterSim;
import com.billcom.eshop.Responce.NumResponse;
import com.billcom.eshop.commons.entities.Num;
import com.billcom.eshop.commons.entities.PhoneType;
import com.billcom.eshop.commons.entities.UtilisateurAll;
import com.billcom.eshop.commons.repositories.NumRepository;
import com.billcom.eshop.commons.repositories.PhoneTypeRepository;
import com.billcom.eshop.commons.repositories.UtilisateurAllRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class NumService implements InterfaceNumService {

    @Autowired
    private NumRepository numRepository;

    @Autowired
    private UtilisateurAllRepository utilisateurAllRepository;

    @Autowired
    private PhoneTypeRepository phoneTypeRepository;


    @Override
    public ResponseEntity<NumResponse> acheterSim(Long utilisateurId, NumRequestAcheterSim numRequest) {
        // Fixer numType à "SIM" par défaut
        String numType = "SIM";

        Optional<Num> optionalNum = numRepository.findFirstByUtilisateurAllIsNull();
        if (!optionalNum.isPresent()) {
            return ResponseEntity.badRequest().body(new NumResponse());
        }

        Num num = optionalNum.get();

        // Générer un numéro de série aléatoire (entre 15 et 17 caractères)
        Long serialNumber = generateRandomSerialNumber();

        Optional<UtilisateurAll> optionalUser = utilisateurAllRepository.findById(utilisateurId);
        if (!optionalUser.isPresent()) {
            return ResponseEntity.badRequest().body(new NumResponse());
        }

        UtilisateurAll utilisateur = optionalUser.get();

        num.setNumType(numType);  // Définir le type de la carte SIM par défaut
        num.setUtilisateurAll(utilisateur);
        num.setNumSerialNumber(serialNumber);
        num.setNumActivationDate(LocalDate.now());
        num.setNumActivationStatus(true);
        numRepository.save(num);

        return ResponseEntity.ok(new NumResponse());
    }

    private Long generateRandomSerialNumber() {
        int length = 15 + new Random().nextInt(3); // Longueur aléatoire entre 15 et 17
        StringBuilder serialNumber = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            serialNumber.append(random.nextInt(10)); // Ajout d'un chiffre aléatoire (0-9)
        }

        return Long.parseLong(serialNumber.toString());
    }



    @Override
    public ResponseEntity<NumResponse> acheterEsim(Long utilisateurId, NumRequest numRequest) {
        String numType = "eSIM";  // Définir numType à "ESIM" par défaut

        Optional<Num> optionalNum = numRepository.findFirstByUtilisateurAllIsNull();
        if (!optionalNum.isPresent()) {
            return ResponseEntity.badRequest().body(new NumResponse());
        }

        Num num = optionalNum.get();

        if (numRequest.getNumImei() == null || numRequest.getNumImei().length() != 15) {
            return ResponseEntity.badRequest().body(new NumResponse());
        }

        Optional<PhoneType> optionalPhoneType = phoneTypeRepository.findById(numRequest.getPhoneTypeId());
        if (!optionalPhoneType.isPresent()) {
            return ResponseEntity.badRequest().body(new NumResponse());
        }

        Optional<UtilisateurAll> optionalUser = utilisateurAllRepository.findById(utilisateurId);
        if (!optionalUser.isPresent()) {
            return ResponseEntity.badRequest().body(new NumResponse());
        }

        UtilisateurAll utilisateur = optionalUser.get();

        num.setNumType(numType);  // Utilisation de numType par défaut à "ESIM"
        num.setUtilisateurAll(utilisateur);
        num.setNumSerialNumber(null);
        num.setNumImei(numRequest.getNumImei());
        num.setPhoneType(optionalPhoneType.get());
        num.setNumActivationDate(LocalDate.now());
        num.setNumActivationStatus(true);
        numRepository.save(num);

        return ResponseEntity.ok(new NumResponse());
    }


    @Override
    public ResponseEntity<NumResponse> changerSimVersEsim(Long numPhoneNumber, Long numSerialNumber, String numImei, Long phoneTypeId) {
        Optional<Num> optionalNum = numRepository.findByNumPhoneNumberAndNumSerialNumberAndNumType(numPhoneNumber, numSerialNumber, "SIM");
        if (!optionalNum.isPresent()) {
            return ResponseEntity.badRequest().body(new NumResponse());
        }

        Num sim = optionalNum.get();

        Optional<PhoneType> optionalPhoneType = phoneTypeRepository.findById(phoneTypeId);
        if (!optionalPhoneType.isPresent()) {
            return ResponseEntity.badRequest().body(new NumResponse());
        }

        PhoneType phoneType = optionalPhoneType.get();

        // Convertir le SIM en eSIM
        sim.setNumType("ESIM");
        sim.setNumImei(numImei);
        sim.setPhoneType(phoneType);
        sim.setNumSerialNumber(null); // Le serial number devient null pour un eSIM
        sim.setNumActivationDate(LocalDate.now());
        sim.setNumActivationStatus(true);
        numRepository.save(sim);

        return ResponseEntity.ok(new NumResponse());
    }

    @Override
    public ResponseEntity<NumResponse> changerEsimVersSim(Long numPhoneNumber, String numImei) {
        Optional<Num> optionalNum = numRepository.findByNumPhoneNumberAndNumType(numPhoneNumber, "ESIM");
        if (!optionalNum.isPresent()) {
            return ResponseEntity.badRequest().body(new NumResponse());
        }

        Num esim = optionalNum.get();

        // Vérification de l'IMEI
        if (!esim.getNumImei().equals(numImei)) {
            return ResponseEntity.badRequest().body(new NumResponse());
        }

        // Génération d'un nouveau numéro de série aléatoire
        Long newSerialNumber = generateRandomSerialNumber();

        // Convertir l'ESIM en SIM
        esim.setNumType("SIM");
        esim.setNumImei(null); // L'IMEI devient null pour un SIM
        esim.setNumSerialNumber(newSerialNumber);
        numRepository.save(esim);

        return ResponseEntity.ok(new NumResponse());
    }

    Long SerialNumber = generateRandomSerialNumber();

    private boolean isValidImei(String imei) {
        return imei != null && imei.matches("^\\d{15}$");
    }

    @Override
    public NumResponse findAllNum() {
        List<Num> nums = numRepository.findAllByNumActivationStatusTrueAndUtilisateurAllIsNotNull();
        NumResponse response = new NumResponse();
        if (nums.isEmpty()) {
            response.setIsSuccessfull(false);
            response.setMessage("No numbers found");
        } else {
            response.setIsSuccessfull(true);
            response.setNums(nums);
        }
        return response;
    }

    @Override
    public NumResponse findAllNumById(Long utilisateurId) {
        List<Num> nums = numRepository.findAllByUtilisateurAllId(utilisateurId);
        NumResponse response = new NumResponse();
        if (nums.isEmpty()) {
            response.setIsSuccessfull(false);
            response.setMessage("No numbers found for the given user ID");
        } else {
            response.setIsSuccessfull(true);
            response.setNums(nums);
        }
        return response;
    }
}




