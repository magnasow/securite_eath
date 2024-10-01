package com.eath.Service;

import com.eath.entite.Ingredient;
import com.eath.entite.Allergene;

import java.util.List;

public interface IngredientService {
    Ingredient addIngredient(Ingredient ingredient);
    List<Ingredient> getAllIngredients();
    Ingredient getIngredientById(Integer idIngredient);
    Ingredient updateIngredient(Integer idIngredient, Ingredient ingredient);
    void deleteIngredient(Integer idIngredient);

    void addAllergeneToIngredient(Integer ingredientId, Integer allergeneId);
    void removeAllergeneFromIngredient(Integer ingredientId, Integer allergeneId);
    void addSubstanceToIngredient(Integer idIngredient, Integer idSubstance);
    void removeSubstanceFromIngredient(Integer idIngredient, Integer idSubstance);
}
