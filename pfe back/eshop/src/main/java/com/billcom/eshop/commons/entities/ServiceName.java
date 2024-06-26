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
@Table(name = "service_name")
public class ServiceName {
    @Id
    @Column(name = "sn_id", nullable = false)
    private Long id;

    @Size(max = 255)
    @NotNull
    @Column(name = "sn_desc", nullable = false)
    private String snDesc;

    @Size(max = 255)
    @NotNull
    @Column(name = "sn_type", nullable = false)
    private String snType;

}