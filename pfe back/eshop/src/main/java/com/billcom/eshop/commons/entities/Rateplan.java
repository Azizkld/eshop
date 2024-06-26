package com.billcom.eshop.commons.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "rateplan")
public class Rateplan {
    @Id
    @Column(name = "rp_id", nullable = false)
    private Long id;

    @Size(max = 30)
    @NotNull
    @Column(name = "rp_desc", nullable = false, length = 30)
    private String rpDesc;

    @Size(max = 255)
    @NotNull
    @Column(name = "rp_price", nullable = false)
    private String rpPrice;

    @Size(max = 255)
    @NotNull
    @Column(name = "rp_type", nullable = false)
    private String rpType;

}