package com.billcom.eshop.commons.dtos;

import com.billcom.eshop.commons.entities.Esim;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Esim}
 */
@Value
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class EsimDto implements Serializable {
    Long id;
    @Size(max = 255)
    String esimImei;
}