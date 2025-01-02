package com.eath.entite;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDateTime;

@Entity
@Table(name = "historique_scans")
@Data
@JsonIgnoreProperties({"utilisateur", "produit"})  // Ignore associations pour éviter récursivité infinie
public class HistoriqueScan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historique_scan")
    private Integer idHistoriqueScan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_personne")
    private Utilisateurs utilisateur;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_produit")
    private Produits produit;

    @Column(name = "date_scan", nullable = false, updatable = false)
    private LocalDateTime dateScan;

    @PrePersist
    protected void onCreate() {
        dateScan = LocalDateTime.now();
    }
}
