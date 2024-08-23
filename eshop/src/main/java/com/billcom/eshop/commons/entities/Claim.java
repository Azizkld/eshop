package com.billcom.eshop.commons.entities;

import com.billcom.eshop.Responce.ClaimResponse;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "claim")
public class Claim  {
    @Id
    @Column(name = "cl_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clid;

    @Size(max = 255)
    @Column(name = "cl_desc")
    private String clDesc;

    @Size(max = 255)
    @Column(name = "cl_reponce")
    private String clReponce;

    @NotNull
    @Column(name = "cl_status", nullable = false)
    private boolean clStatus;

    @Column(name = "cl_date")
    private LocalDate clDate;

    // Relation avec l'utilisateur
    @ManyToOne
    @JoinColumn(name = "utilisateur_id", nullable = false)
    private UtilisateurAll utilisateurAll;


}