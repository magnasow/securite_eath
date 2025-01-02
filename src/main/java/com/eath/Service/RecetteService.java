package com.eath.Service;

import com.eath.entite.Recette;
import java.util.List;

public interface RecetteService {
    List<Recette> getRecettes(); // Récupérer toutes les recettes
    List<Recette> getRecettesByLanguage(String lang); // Récupérer les recettes par langue
}
