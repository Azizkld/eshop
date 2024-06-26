package com.billcom.eshop.commons.dtos;

import com.billcom.eshop.commons.entities.ContractAll;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link ContractAll}
 */
@Value
@Builder
public class ContractAllDto implements Serializable {
    Long id;
    LocalDate coActivDate;
    @NotNull
    Long coCode;
    LocalDate coExpirDate;
    @Size(max = 255)
    String coQrCode;
    @Size(max = 255)
    String coSigned;
    Boolean coStatus;
}