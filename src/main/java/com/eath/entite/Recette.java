package com.eath.entite;

import lombok.Data;
import java.util.Map;

@Data
public class Recette {
    private String image; // Chemin de l'image
    private Map<String, String> titre; // Titre dans différentes langues
    private Map<String, String> description; // Description dans différentes langues
    private String auteur; // Nom de l'auteur
    private String date; // Date de publication au format String
}
