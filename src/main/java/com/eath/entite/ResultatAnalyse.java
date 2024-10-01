package com.eath.entite;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;

@Entity
@Table(name = "resultat_analyse")
@Data
public class ResultatAnalyse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resultat")
    private Integer idResultat;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_personne", nullable = false)
    @JsonIgnore
    private Utilisateurs utilisateur;

    @Column(name = "resultat_allergene", columnDefinition = "TEXT")
    private String resultatAllergene;

    @Column(name = "conformite_halal")
    private Boolean conformiteHalal;

    @Column(name = "substance_nocive", columnDefinition = "TEXT")
    private String substanceNocive;

    @CreationTimestamp
    @Column(name = "date_creation", updatable = false)
    private LocalDateTime dateCreation;

    @UpdateTimestamp
    @Column(name = "date_modification")
    private LocalDateTime dateModification;
}
