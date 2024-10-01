package com.eath.Service;

import com.eath.entite.ProduitIngredient;
import com.eath.entite.ProduitIngredientKey;

import java.util.List;

public interface ProduitIngredientService {
    ProduitIngredient addProduitIngredient(ProduitIngredient produitIngredient);
    List<ProduitIngredient> getAllProduitIngredients();
    ProduitIngredient getProduitIngredientById(ProduitIngredientKey id);
    ProduitIngredient updateProduitIngredient(ProduitIngredientKey id, ProduitIngredient produitIngredient);
    void deleteProduitIngredient(ProduitIngredientKey id);
}
