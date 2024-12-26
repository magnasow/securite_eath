package com.eath.web;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class PasswordResetWithConfirmationRequest {

    @NotEmpty(message = "Le nouveau mot de passe ne peut pas être vide")
    private String newPassword;

    @NotEmpty(message = "La confirmation du mot de passe ne peut pas être vide")
    private String confirmPassword;


}
