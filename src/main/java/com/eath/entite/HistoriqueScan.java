package com.eath.entite;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;

@Entity
@Table(name = "historique_scans")
@Data
public class HistoriqueScan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historique_scan")
    private Integer idHistoriqueScan;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_personne", nullable = false)
    @JsonIgnore
    private Utilisateurs utilisateur;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_produit", nullable = false)
    @JsonIgnore
    private Produits produit;

    @Column(name = "date_scan", nullable = false, updatable = false)
    private LocalDateTime dateScan;

    @PrePersist
    protected void onCreate() {
        dateScan = LocalDateTime.now();
    }
}
