package com.billcom.eshop.commons.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "num")
public class Num {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "num_id", nullable = false)
    private Long numid;


    @Column(name = "num_activation_date")
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


    @NotNull
    @Column(name = "num_type", nullable = false)
    private String numType;

    @NotNull
    @Column(name = "num_activation_status", nullable = false)
    private boolean numActivationStatus;

    @NotNull
    @Column(name = "num_phone_number", nullable = false)
    private Long numPhoneNumber;

    @Size(max = 255)
    @Column(name = "num_serial_number")
    private Long numSerialNumber;

    @Size(max = 255)
    @Column(name = "num_imei")
    private String numImei;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private UtilisateurAll utilisateurAll;

    @ManyToOne
    @JoinColumn(name = "phone_type_id")
    private PhoneType phoneType;

    @JsonIgnore
    @OneToMany(mappedBy = "num")
    private List<ContractAll> contracts = new ArrayList<>();


}