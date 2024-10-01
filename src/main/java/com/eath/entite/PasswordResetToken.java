package com.eath.entite;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class PasswordResetToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateurs utilisateur;

    private LocalDateTime expiryDate;

    // Constructeur par défaut
    public PasswordResetToken() {}

    // Constructeur avec les paramètres token et utilisateur
    public PasswordResetToken(String token, Utilisateurs utilisateur) {
        this.token = token;
        this.utilisateur = utilisateur;
        this.expiryDate = LocalDateTime.now().plusHours(1); // Expiration dans 1 heure
    }

    // Méthode pour vérifier si le token est expiré
    public boolean isExpired() {
        return LocalDateTime.now().isAfter(this.expiryDate);
    }
}
