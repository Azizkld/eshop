package com.billcom.eshop.commons.dtos;

import com.billcom.eshop.commons.entities.Num;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link Num}
 */
@Value
@Builder
public class NumDto implements Serializable {
    Long id;
    @NotNull
    LocalDate numActivationDate;
    @NotNull
    @Size(max = 255)
    String numOperatorName;
    @NotNull
    Long numPinCode;
    @NotNull
    Long numPukCode;
    @NotNull
    @Size(max = 255)
    String numQrCode;
    @NotNull
    @Size(max = 255)
    String numType;
    @NotNull
    LocalDate numActivationStatus;
    @NotNull
    Long numPhoneNumber;
}