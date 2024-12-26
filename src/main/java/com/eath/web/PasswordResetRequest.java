package com.eath.web;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class PasswordResetRequest {

    @Email(message = "Adresse e-mail invalide")
    @NotEmpty(message = "L'email ne peut pas être vide")
    private String email;
}
