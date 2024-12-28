package com.eath.web;

import jakarta.validation.constraints.*;

import lombok.Data;

@Data
public class CommentaireRequest {

    @NotNull(message = "Utilisateur ID cannot be null.")
    private Integer utilisateurId;

    @NotBlank(message = "Commentaire cannot be empty.")
    private String commentaire;

    @Min(value = 0, message = "Note must be at least 0.")
    @Max(value = 10, message = "Note cannot exceed 10.")
    private Integer note;
}
