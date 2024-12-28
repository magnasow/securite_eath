package com.eath.entite;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.*;

@Entity
@Table(name = "utilisateur")
@DiscriminatorValue("UTILISATEUR")
@Data
public class Utilisateurs extends Personne {

    @Column(name = "preferences", columnDefinition = "TEXT")
    private String preferences;

    @Column(name = "niveau_abonnement")
    private String niveauAbonnement;

    @Column(name = "date_modification", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Timestamp dateModification;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "utilisateur_roles",
            joinColumns = @JoinColumn(name = "utilisateur_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    // Méthode pour convertir une liste en chaîne de préférences
    public void setPreferences(List<String> preferencesList) {
        if (preferencesList != null && !preferencesList.isEmpty()) {
            this.preferences = String.join(", ", preferencesList);
        } else {
            this.preferences = null;
        }
    }

    // Méthode pour récupérer les préférences sous forme de liste
    public List<String> getPreferences() {
        if (this.preferences != null && !this.preferences.isEmpty()) {
            return Arrays.asList(this.preferences.split(",\\s*"));
        }
        return new ArrayList<>();
    }

}