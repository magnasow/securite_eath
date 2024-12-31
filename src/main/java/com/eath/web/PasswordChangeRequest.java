package com.eath.web;

import lombok.Data;

@Data
public class PasswordChangeRequest {
    private Integer idPersonne;  // Utiliser idPersonne pour correspondre à la table Utilisateur
    private String oldPassword;
    private String newPassword;
}

