package com.eath.entite;

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

    @ManyToOne
    @JoinColumn(name = "id_personne", nullable = false)
    private Utilisateurs utilisateur;

    @ManyToOne
    @JoinColumn(name = "id_produit", nullable = false)
    private Produits produit;

    @Column(name = "date_scan", nullable = false, updatable = false)
    private LocalDateTime dateScan;

    @PrePersist
    protected void onCreate() {
        dateScan = LocalDateTime.now();
    }
}
