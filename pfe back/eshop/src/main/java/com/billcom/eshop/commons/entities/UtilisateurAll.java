package com.billcom.eshop.commons.entities;

import com.billcom.eshop.auth.models.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "utilisateur_all")
public class UtilisateurAll {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ut_id")
    private Long id;

    @Size(max = 255)
    @Column(name = "ut_adresse")
    private String utAdresse;

    @Column(name = "ut_cin")
    private String utCin;

    @Size(max = 255)
    @Column(name = "ut_city")
    private String utCity;

    @Size(max = 255)
    @Column(name = "ut_country")
    private String utCountry;

    @Size(max = 255)
    @Column(name = "ut_f_name")
    private String utFName;

    @Size(max = 255)
    @Column(name = "ut_l_name")
    private String utLName;

    @Size(max = 255)
    @Column(name = "ut_mail")
    private String utMail;

    @Size(max = 255)
    @Column(name = "ut_password")
    private String utPassword;

    @Column(name = "ut_zip_code")
    private Integer utZipCode;

    @Enumerated(EnumType.STRING)
    private Role role;

}