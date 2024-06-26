package com.billcom.eshop.commons.dtos;

import com.billcom.eshop.auth.models.Role;
import com.billcom.eshop.commons.entities.UtilisateurAll;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * DTO for {@link UtilisateurAll}
 */
@Value
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class UtilisateurAllDto implements Serializable, UserDetails {
    Long id;
    @Size(max = 255)
    String utAdresse;
    String utCin;
    @Size(max = 255)
    String utCity;
    @Size(max = 255)
    String utCountry;
    @Size(max = 255)
    String utFName;
    @Size(max = 255)
    String utLName;
    @Size(max = 255)
    String utMail;
    @Size(max = 255)
    String utPassword;
    Integer utZipCode;
    Role role;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return utPassword;
    }

    @Override
    public String getUsername() {
        return utCin.toString();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}