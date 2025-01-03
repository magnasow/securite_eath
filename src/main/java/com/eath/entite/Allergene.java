package com.eath.entite;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "allergenes")
@Data
@NoArgsConstructor
public class Allergene {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_allergene")
    private int id;


    // Utiliser une colonne simple pour stocker les noms d'allergènes séparés par des virgules
    @Column(name = "nom_allergene", unique = true)
    private String nomAllergenes;

    @Column(name = "description_allergene")
    private String descriptionAllergene;

    @Column(name = "date_creation", updatable = false)
    private LocalDateTime dateCreation;

    @Column(name = "date_modification")
    private LocalDateTime dateModification;

    @ManyToMany(mappedBy = "allergenes")
    private Set<Ingredient> ingredients = new HashSet<>();

    @PrePersist
    protected void onCreate() {
        dateCreation = LocalDateTime.now();
        dateModification = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        dateModification = LocalDateTime.now();
    }
}
