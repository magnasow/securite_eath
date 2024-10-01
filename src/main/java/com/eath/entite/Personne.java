package com.eath.entite;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Personne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_personne")
    private Integer idPersonne;

    @NotBlank(message = "Le nom est obligatoire.")
    @Size(max = 50, message = "Le nom ne doit pas dépasser 50 caractères.")
    @Column(name = "nom_personne", nullable = false)
    private String nomPersonne;

    @NotBlank(message = "Le prénom est obligatoire.")
    @Size(max = 50, message = "Le prénom ne doit pas dépasser 50 caractères.")
    @Column(name = "prenom_personne", nullable = false)
    private String prenomPersonne;

    @NotBlank(message = "Le mot de passe est obligatoire.")
    @Size(min = 8, message = "Le mot de passe doit contenir au moins 8 caractères.")
    @Column(name = "mot_de_passe", nullable = false)
    private String motDePasse;

    @NotBlank(message = "L'email est obligatoire.")
    @Email(message = "L'email doit être valide.")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Size(max = 255, message = "La photo de profil ne doit pas dépasser 255 caractères.")
    @Column(name = "photo_de_profil")
    private String photoDeProfil;

    @Column(name = "date_creation", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp dateCreation;

    @DecimalMin(value = "0.5", message = "La taille doit être supérieure à 0.5 mètre.")
    @DecimalMax(value = "2.5", message = "La taille doit être inférieure à 2.5 mètres.")
    @Column(name = "taille")
    private BigDecimal taille;

    @DecimalMin(value = "10", message = "Le poids doit être supérieur à 10 kg.")
    @DecimalMax(value = "500", message = "Le poids doit être inférieur à 500 kg.")
    @Column(name = "poids")
    private BigDecimal poids;

    @Min(value = 0, message = "L'âge ne peut pas être négatif.")
    @Max(value = 150, message = "L'âge ne peut pas dépasser 150 ans.")
    @Column(name = "age")
    private Integer age;

    @Pattern(regexp = "M|F", message = "Le sexe doit être 'M' ou 'F'.")
    @Column(name = "sexe")
    private String sexe;

    @Size(max = 255, message = "Les conditions médicales ne doivent pas dépasser 255 caractères.")
    @Column(name = "conditions_medicales")
    private String conditionsMedicales;

    @PrePersist
    protected void onCreate() {
        if (this.dateCreation == null) {
            this.dateCreation = new Timestamp(System.currentTimeMillis());
        }
    }
}
