package com.eath.Service.Implement;

import com.eath.Service.IngredientService;
import com.eath.dao.IngredientRepository;
import com.eath.dao.AllergeneRepository;
import com.eath.dao.SubstanceNociveRepository;
import com.eath.entite.Ingredient;
import com.eath.entite.Allergene;
import com.eath.entite.SubstanceNocive;
import com.eath.exception.IngredientNotFoundException;
import com.eath.exception.SubstanceNociveNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;
    private final AllergeneRepository allergeneRepository;
    private final SubstanceNociveRepository substanceNociveRepository;

    @Override
    public Ingredient addIngredient(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    @Override
    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    @Override
    public Ingredient getIngredientById(Integer idIngredient) {
        return ingredientRepository.findById(idIngredient)
                .orElseThrow(() -> new IngredientNotFoundException("Ingredient not found with id: " + idIngredient));
    }

    @Override
    public Ingredient updateIngredient(Integer idIngredient, Ingredient ingredient) {
        Ingredient existingIngredient = getIngredientById(idIngredient);
        existingIngredient.setNomIngredient(ingredient.getNomIngredient());
        existingIngredient.setDescriptionIngredient(ingredient.getDescriptionIngredient());
        existingIngredient.setNormeHalal(ingredient.getNormeHalal());
        return ingredientRepository.save(existingIngredient);
    }

    @Override
    public void deleteIngredient(Integer idIngredient) {
        if (!ingredientRepository.existsById(idIngredient)) {
            throw new IngredientNotFoundException("Ingredient not found with id: " + idIngredient);
        }
        ingredientRepository.deleteById(idIngredient);
    }

    @Override
    public void addAllergeneToIngredient(Integer ingredientId, Integer allergeneId) {
        Ingredient ingredient = getIngredientById(ingredientId);
        Allergene allergene = allergeneRepository.findById(allergeneId)
                .orElseThrow(() -> new RuntimeException("Allergene not found with id: " + allergeneId));

        ingredient.getAllergenes().add(allergene);
        ingredientRepository.save(ingredient);
    }

    @Override
    public void removeAllergeneFromIngredient(Integer ingredientId, Integer allergeneId) {
        Ingredient ingredient = getIngredientById(ingredientId);
        Allergene allergene = allergeneRepository.findById(allergeneId)
                .orElseThrow(() -> new RuntimeException("Allergene not found with id: " + allergeneId));

        ingredient.getAllergenes().remove(allergene);
        ingredientRepository.save(ingredient);
    }

    @Override
    public void addSubstanceToIngredient(Integer idIngredient, Integer idSubstance) {
        Ingredient ingredient = getIngredientById(idIngredient);
        SubstanceNocive substance = substanceNociveRepository.findById(idSubstance)
                .orElseThrow(() -> new SubstanceNociveNotFoundException("Substance not found with id: " + idSubstance));

        ingredient.getSubstancesNocives().add(substance);
        ingredientRepository.save(ingredient);
    }

    @Override
    public void removeSubstanceFromIngredient(Integer idIngredient, Integer idSubstance) {
        Ingredient ingredient = getIngredientById(idIngredient);
        SubstanceNocive substance = substanceNociveRepository.findById(idSubstance)
                .orElseThrow(() -> new SubstanceNociveNotFoundException("Substance not found with id: " + idSubstance));

        ingredient.getSubstancesNocives().remove(substance);
        ingredientRepository.save(ingredient);
    }
}
