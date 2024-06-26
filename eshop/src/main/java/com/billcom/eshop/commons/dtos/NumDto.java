package com.billcom.eshop.commons.dtos;

import com.billcom.eshop.commons.entities.Num;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link Num}
 */
@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class NumDto implements Serializable {
    Long numid;
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
    String numType;
    boolean numActivationStatus;
    @NotNull
    Long numPhoneNumber;
    Long numSerialNumber;
    @Size(max = 255)
    String numImei;
}