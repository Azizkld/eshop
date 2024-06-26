package com.billcom.eshop.controller;

import com.billcom.eshop.Request.UtilisateurAllRequest;
import com.billcom.eshop.Responce.UtilisateurAllResponse;
import com.billcom.eshop.InterfaceService.InterfaceUtilisateurAllService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@SecurityRequirement(name = "bearer-key")
@RequestMapping("/api/UtilisateurAll")
public class UtilisateurAllController {

    @Autowired
    InterfaceUtilisateurAllService utilisateurAllService;

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

}
