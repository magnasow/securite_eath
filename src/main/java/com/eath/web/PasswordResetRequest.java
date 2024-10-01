package com.eath.web;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class PasswordResetRequest {
    @Email(message = "Adresse e-mail invalide")
    @NotEmpty(message = "L'email ne peut pas être vide")
    private String email; // L'adresse e-mail de l'utilisateur

    @NotEmpty(message = "Le jeton ne peut pas être vide")
    private String token; // Le jeton de réinitialisation envoyé par email

    @NotEmpty(message = "Le nouveau mot de passe ne peut pas être vide")
    private String newPassword; // Nouveau mot de passe
}
