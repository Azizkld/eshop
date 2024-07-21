package com.billcom.eshop.controller;

import com.billcom.eshop.Request.UtilisateurAllRequest;
import com.billcom.eshop.Responce.UtilisateurAllResponse;
import com.billcom.eshop.InterfaceService.InterfaceUtilisateurAllService;
import com.billcom.eshop.commons.entities.UtilisateurAll;
import com.billcom.eshop.service.ImageService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin("*")
@SecurityRequirement(name = "bearer-key")
@RequestMapping("/api/UtilisateurAll")
public class UtilisateurAllController {

    @Autowired
    InterfaceUtilisateurAllService utilisateurAllService;
    @Autowired
    ImageService imageService;

    @DeleteMapping("supprimerUtilisateurId")
    public UtilisateurAllResponse deleteUtilisateur(@RequestParam long id) {
        return utilisateurAllService.deleteUtilisateur(id);
    }

    @GetMapping("afficherListeUtilisateurs")
    public UtilisateurAllResponse findAllUtilisateurs() {
        return utilisateurAllService.findAllUtilisateurs();
    }

    @GetMapping("afficherUtilisateurId")
    public UtilisateurAllResponse findUtilisateurById(@RequestParam long id) {
        return utilisateurAllService.findUtilisateurById(id);
    }

    @PutMapping("modifierUtilisateurId")
    public UtilisateurAllResponse updateUtilisateur(@RequestParam long id, @RequestBody UtilisateurAllRequest utilisateurAllRequest) {
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
