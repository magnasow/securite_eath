package com.eath.entite;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "substances_nocives")
@Data
public class SubstanceNocive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_substance")
    private Integer idSubstance;

    @Column(name = "nom_substance", nullable = false, length = 255)
    private String nomSubstance;

    @Column(name = "type_substance", length = 50)
    private String typeSubstance;

    @Column(name = "date_creation", nullable = false, updatable = false)
    private LocalDateTime dateCreation;

    @Column(name = "date_modification", nullable = false)
    private LocalDateTime dateModification;

    @ManyToMany(mappedBy = "substancesNocives")
    private Set<Ingredient> ingredients = new HashSet<>();

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.dateCreation = now;
        this.dateModification = now;
    }

    @PreUpdate
    protected void onUpdate() {
        this.dateModification = LocalDateTime.now();
    }


}
