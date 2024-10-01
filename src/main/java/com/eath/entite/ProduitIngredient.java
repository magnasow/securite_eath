package com.eath.entite;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "produits_ingredients")
@Data

public class ProduitIngredient {

    @EmbeddedId
    private ProduitIngredientKey id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @MapsId("idProduit")
    @JoinColumn(name = "id_produit", insertable = false, updatable = false)
    private Produits produit;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @MapsId("idIngredient")
    @JoinColumn(name = "id_ingredient", insertable = false, updatable = false)
    private Ingredient ingredient;

    @Column(name = "quantite", nullable = false)
    private Double quantite;

    @Column(name = "date_creation", nullable = false, updatable = false)
    private LocalDateTime dateCreation;

    @Column(name = "date_modification", nullable = false)
    private LocalDateTime dateModification;

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
