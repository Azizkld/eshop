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
@Table(name = "num")
public class Num {
    @Id
    @Column(name = "num_id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "num_activation_date", nullable = false)
    private LocalDate numActivationDate;

    @Size(max = 255)
    @NotNull
    @Column(name = "num_operator_name", nullable = false)
    private String numOperatorName;

    @NotNull
    @Column(name = "num_pin_code", nullable = false)
    private Long numPinCode;

    @NotNull
    @Column(name = "num_puk_code", nullable = false)
    private Long numPukCode;

    @Size(max = 255)
    @NotNull
    @Column(name = "num_qr_code", nullable = false)
    private String numQrCode;

    @Size(max = 255)
    @NotNull
    @Column(name = "num_type", nullable = false)
    private String numType;

    @NotNull
    @Column(name = "num_activation_status", nullable = false)
    private LocalDate numActivationStatus;

    @NotNull
    @Column(name = "num_phone_number", nullable = false)
    private Long numPhoneNumber;

}