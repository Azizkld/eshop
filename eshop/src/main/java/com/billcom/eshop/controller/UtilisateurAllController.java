package com.billcom.eshop.controller;

import com.billcom.eshop.Request.UtilisateurAllRequest;
import com.billcom.eshop.Responce.UtilisateurAllResponse;
import com.billcom.eshop.InterfaceService.InterfaceUtilisateurAllService;
import com.billcom.eshop.commons.entities.UtilisateurAll;
import com.billcom.eshop.commons.repositories.UtilisateurAllRepository;
import com.billcom.eshop.service.ImageService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@SecurityRequirement(name = "bearer-key")
@RequestMapping("/api/UtilisateurAll")
public class UtilisateurAllController {

    @Autowired
    InterfaceUtilisateurAllService utilisateurAllService;
    @Autowired
    ImageService imageService;
    @Autowired
    private UtilisateurAllRepository utilisateurAllRepository;

    @DeleteMapping("supprimerUtilisateurId")
    public UtilisateurAllResponse deleteUtilisateur(@RequestParam long id) {
        return utilisateurAllService.deleteUtilisateur(id);
    }

    @GetMapping("afficherListeUtilisateurs")
    public ResponseEntity<List<UtilisateurAll>> findAllUtilisateurs() {
        List<UtilisateurAll> utilisateurs = utilisateurAllRepository.findAll();
        // Filtrer les utilisateurs pour exclure l'utilisateur avec l'ID 2
        List<UtilisateurAll> utilisateursFiltrés = utilisateurs.stream()
                .filter(utilisateur -> !utilisateur.getId().equals(2L))
                .collect(Collectors.toList());

        return ResponseEntity.ok(utilisateursFiltrés);
    }

    @GetMapping("afficherUtilisateurId")
    public UtilisateurAllResponse findUtilisateurById(@RequestParam long id) {
        return utilisateurAllService.findUtilisateurById(id);
    }

    @PutMapping("modifierUtilisateurId")
    public UtilisateurAllResponse updateUtilisateur(
            @RequestParam long id,
            @RequestBody UtilisateurAllRequest utilisateurAllRequest) {
        return utilisateurAllService.updateUtilisateur(id, utilisateurAllRequest);
    }



    @PostMapping(value = "upload")
    public ResponseEntity<String> uploadImage(@RequestParam MultipartFile file, @RequestParam Long id) {
        // us.uploadImage(username,file.getOriginalFilename());
        String nameImage;
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        nameImage = imageService.getAlphaNumericString(20) + fileName;
System.out.println("test image "+nameImage);
        utilisateurAllService.UtilisateurUpdateImage(id,nameImage);

        return this.imageService.uploadToLocalFileSystem(file, nameImage);
    }

}
