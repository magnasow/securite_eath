package com.eath.entite;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "norme_halal")
@Data
public class NormeHalal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_norme_halal")
    private Integer idNormeHalal;

    @Column(name = "description_norme_halal", columnDefinition = "TEXT")
    private String descriptionNormeHalal;

    @Column(name = "est_halal")
    private Boolean estHalal;

    @Column(name = "date_creation", nullable = false, updatable = false)
    private LocalDateTime dateCreation;

    @Column(name = "date_modification", nullable = false)
    private LocalDateTime dateModification;

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
