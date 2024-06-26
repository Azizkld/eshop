package com.billcom.eshop.commons.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    @Column(name = "co_id", nullable = false)
    private Long id;

    @Column(name = "co_activ_date")
    private LocalDate coActivDate;

    @NotNull
    @Column(name = "co_code", nullable = false)
    private Long coCode;

    @Column(name = "co_expir_date")
    private LocalDate coExpirDate;

    @Size(max = 255)
    @Column(name = "co_qr_code")
    private String coQrCode;

    @Size(max = 255)
    @Column(name = "co_signed")
    private String coSigned;

    @Column(name = "co_status")
    private Boolean coStatus;

}