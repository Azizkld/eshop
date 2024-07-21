package com.billcom.eshop.commons.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "rateplan")
public class Rateplan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rp_id", nullable = false)
    private Long id;

    @Size(max = 30)
    @NotNull
    @Column(name = "rp_name", nullable = false, length = 30)
    private String rpName;

    @Size(max = 30)
    @NotNull
    @Column(name = "rp_desc", nullable = false, length = 30)
    private String rpDesc;

    @Size(max = 255)
    @NotNull
    @Column(name = "rp_price", nullable = false)
    private Long rpPrice;

    @Column(name = "rp_validationJours",nullable = false)
    private Integer rpValidationDays;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "rateplan_service",
            joinColumns = @JoinColumn(name = "rp_id"),
            inverseJoinColumns = @JoinColumn(name = "sn_id")
    )
    private Set<ServiceName> services;

    @JsonIgnore
    @OneToMany(mappedBy = "rateplan")
    private List<ContractAll> contracts;

}