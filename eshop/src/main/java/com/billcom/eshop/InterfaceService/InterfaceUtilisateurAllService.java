package com.billcom.eshop.InterfaceService;

import com.billcom.eshop.Request.UtilisateurAllRequest;
import com.billcom.eshop.Responce.UtilisateurAllResponse;

public interface InterfaceUtilisateurAllService {

    UtilisateurAllResponse deleteUtilisateur(Long id);
    UtilisateurAllResponse findAllUtilisateurs();
    UtilisateurAllResponse findUtilisateurById(Long id);
    UtilisateurAllResponse updateUtilisateur(Long id, UtilisateurAllRequest utilisateurAllRequest);
}
