package com.billcom.eshop.commons.dtos;

import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.billcom.eshop.commons.entities.Claim}
 */
@Value
@Builder
public class ClaimDto implements Serializable {
    Long id;
    @Size(max = 255)
    String clDesc;
}