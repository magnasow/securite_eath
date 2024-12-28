package com.eath.web;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentaireResponse {
    private Integer idCommentaire;
    private String commentaire;
    private Integer note;
    private LocalDateTime dateCreation;
    private LocalDateTime dateModification;
    private String nomPersonne;
    private String prenomPersonne;
    private String photoDeProfil;
}
