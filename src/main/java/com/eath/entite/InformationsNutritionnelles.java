package com.eath.entite;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "informations_nutritionnelles")
@Data
public class InformationsNutritionnelles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_information")
    private Integer idInformation;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_produit", nullable = false)
    private Produits produit;

    @Column(name = "calories", precision = 10, scale = 2)
    private BigDecimal calories;

    @Column(name = "proteines", precision = 10, scale = 2)
    private BigDecimal proteines;

    @Column(name = "graisses", precision = 10, scale = 2)
    private BigDecimal graisses;

    @Column(name = "glucides", precision = 10, scale = 2)
    private BigDecimal glucides;

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
