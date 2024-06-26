package com.billcom.eshop.commons.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "claim")
public class Claim {
    @Id
    @Column(name = "cl_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clid;

    @Size(max = 255)
    @Column(name = "cl_desc")
    private String clDesc;

}