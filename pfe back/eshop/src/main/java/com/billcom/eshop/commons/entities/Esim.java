package com.billcom.eshop.commons.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "esim")
public class Esim {
    @Id
    @Column(name = "esim_id", nullable = false)
    private Long id;

    @Size(max = 255)
    @Column(name = "esim_imei")
    private String esimImei;

}