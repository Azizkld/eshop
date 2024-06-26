package com.billcom.eshop.commons.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ESIM")
public class Esim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "esim_id", nullable = false)
    private Long seimId;
}
