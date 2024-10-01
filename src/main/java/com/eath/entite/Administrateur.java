package com.eath.entite;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "administrateur")
@DiscriminatorValue("ADMINISTRATEUR")
@Data
public class Administrateur extends Personne {

    @Column(name = "niveau_admin")
    private String niveauAdmin;

    @Column(name = "privileges")
    private String privileges;

    @Column(name = "date_modification", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Timestamp dateModification;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "administrateur_roles",
            joinColumns = @JoinColumn(name = "administrateur_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();
}