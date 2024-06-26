package com.billcom.eshop.commons.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "phone_type")
public class PhoneType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ph_id", nullable = false)
    private Long phid;

    @Size(max = 255)
    @Column(name = "ph_name")
    private String phName;

}