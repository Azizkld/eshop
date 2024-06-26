package com.billcom.eshop.commons.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "SIM")
public class Sim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sim_id", nullable = false)
    private Long simId;
}
