package com.billcom.eshop.Request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class NumAjoutRequest {
    @NotNull
    private String numOperatorName;

    @NotNull
    @Digits(integer = 4, fraction = 0, message = "Le code PIN doit contenir exactement 4 chiffres")
    private Long numPinCode;

    @NotNull
    @Digits(integer = 4, fraction = 0, message = "Le code PUK doit contenir exactement 4 chiffres")
    private Long numPukCode;

    @NotNull
    private String numQrCode;

    @NotNull
    private boolean numActivationStatus ;

    @NotNull
    @Pattern(regexp = "^5\\d{7}$", message = "Le numéro de téléphone doit contenir exactement 8 chiffres et commencer par 5")
    private Long numPhoneNumber;
}
