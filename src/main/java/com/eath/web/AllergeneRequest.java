package com.eath.web;

import com.eath.entite.Allergene;
import lombok.Data;

import java.util.List;

@Data
public class AllergeneRequest {
    private List<String> nomAllergenes;  // Liste des allergènes
    private String descriptionAllergene;

    // Constructeur par défaut pour la désérialisation
    public AllergeneRequest() {}

    // Constructeur pour mapper l'entité Allergene en DTO
    public AllergeneRequest(Allergene allergene) {
        this.nomAllergenes = List.of(allergene.getNomAllergenes());  // Conversion en liste
        this.descriptionAllergene = allergene.getDescriptionAllergene();
    }
}
