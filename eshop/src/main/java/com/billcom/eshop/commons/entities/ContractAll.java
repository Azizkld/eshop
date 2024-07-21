package com.billcom.eshop.commons.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "contract_all")
public class ContractAll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "co_id", nullable = false)
    private Long id;

    @Column(name = "co_activ_date")
    private LocalDate coActivDate;

    @Column(name = "co_expir_date")
    private LocalDate coExpirDate;

    @NotNull
    @Column(name = "co_code", nullable = false)
    private Long coCode;

    @Column(name = "co_status")
    private Boolean coStatus;

    @ManyToOne
    @JoinColumn(name = "num_id", nullable = false)
    private Num num;

    @ManyToOne
    @JoinColumn(name = "rp_id", nullable = false)
    private Rateplan rateplan;

    @ManyToOne
    @JoinColumn(name = "ut_id", nullable = false)
    private UtilisateurAll utilisateurAll;

}