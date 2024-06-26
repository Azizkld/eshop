package com.billcom.eshop.commons.dtos;

import com.billcom.eshop.commons.entities.Rateplan;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link Rateplan}
 */
@Value
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class RateplanDto implements Serializable {
    Long id;
    @NotNull
    @Size(max = 30)
    String rpDesc;
    @NotNull
    @Size(max = 255)
    String rpPrice;
    @NotNull
    @Size(max = 255)
    String rpType;


}