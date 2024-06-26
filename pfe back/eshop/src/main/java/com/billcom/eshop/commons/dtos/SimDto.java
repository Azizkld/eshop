package com.billcom.eshop.commons.dtos;

import com.billcom.eshop.commons.entities.Sim;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Sim}
 */
@Value
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class SimDto implements Serializable {
    Long id;
    @Size(max = 255)
    String simSerialNumber;
}