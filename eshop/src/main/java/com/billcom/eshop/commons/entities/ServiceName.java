package com.billcom.eshop.commons.entities;

import com.billcom.eshop.commons.Enum.ServiceType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "service_name")
public class ServiceName {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sn_id", nullable = false)
    private Long id;

    @Size(max = 255)
    @NotNull
    @Column(name = "sn_desc", nullable = false)
    private String snDesc;

    @Size(max = 255)
    @NotNull
    @Column(name = "sn_name", nullable = false)
    private String snName;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "sn_type", nullable = false)
    private ServiceType snType;

    @ManyToMany(mappedBy = "services")
    private Set<Rateplan> rateplans;

}