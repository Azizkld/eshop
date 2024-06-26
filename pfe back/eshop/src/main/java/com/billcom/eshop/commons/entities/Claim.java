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
@Table(name = "claim")
public class Claim {
    @Id
    @Column(name = "cl_id", nullable = false)
    private Long id;

    @Size(max = 255)
    @Column(name = "cl_desc")
    private String clDesc;

}