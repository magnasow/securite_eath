package com.eath.entite;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity // Mark this class as an entity
public class PasswordResetToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-generate the ID
    private Long id;  // Primary key for the entity

    private String token;  // Le token unique généré pour la réinitialisation du mot de passe

    @ManyToOne  // A user can have many reset tokens
    @JoinColumn(name = "utilisateur_id")  // Foreign key for the user
    private Utilisateurs utilisateur;  // L'utilisateur auquel le token appartient

    private LocalDateTime expirationDate;  // La date d'expiration du token

    // Méthode pour vérifier si le token est expiré
    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expirationDate);
    }
}
