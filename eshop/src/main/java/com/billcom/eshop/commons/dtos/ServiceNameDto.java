package com.billcom.eshop.commons.dtos;

import com.billcom.eshop.commons.entities.ServiceName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link ServiceName}
 */
@Value
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ServiceNameDto implements Serializable {
    Long id;
    @NotNull
    @Size(max = 255)
    String snDesc;
    @NotNull
    @Size(max = 255)
    String snType;
}