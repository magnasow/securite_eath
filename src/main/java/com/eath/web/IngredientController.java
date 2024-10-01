package com.eath.web;

import com.eath.Service.IngredientService;
import com.eath.entite.Ingredient;
import com.eath.entite.Allergene;
import com.eath.entite.SubstanceNocive;
import com.eath.exception.IngredientNotFoundException;
import com.eath.exception.SubstanceNociveNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/api/ingredients")
@RequiredArgsConstructor
@Validated
public class IngredientController {

    private final IngredientService ingredientService;

    @PostMapping
    public ResponseEntity<Ingredient> createIngredient(@Valid @RequestBody Ingredient ingredient) {
        Ingredient savedIngredient = ingredientService.addIngredient(ingredient);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedIngredient);
    }

    @GetMapping
    public ResponseEntity<List<Ingredient>> getAllIngredients() {
        List<Ingredient> ingredients = ingredientService.getAllIngredients();
        return ResponseEntity.ok(ingredients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> getIngredientById(@PathVariable Integer id) {
        Ingredient ingredient = ingredientService.getIngredientById(id);
        return ResponseEntity.ok(ingredient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ingredient> updateIngredient(
            @PathVariable Integer id,
            @Valid @RequestBody Ingredient ingredient
    ) {
        Ingredient updatedIngredient = ingredientService.updateIngredient(id, ingredient);
        return ResponseEntity.ok(updatedIngredient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngredient(@PathVariable Integer id) {
        ingredientService.deleteIngredient(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{ingredientId}/allergenes/{allergeneId}")
    public ResponseEntity<Void> addAllergeneToIngredient(
            @PathVariable Integer ingredientId,
            @PathVariable Integer allergeneId) {
        ingredientService.addAllergeneToIngredient(ingredientId, allergeneId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{ingredientId}/allergenes/{allergeneId}")
    public ResponseEntity<Void> removeAllergeneFromIngredient(
            @PathVariable Integer ingredientId,
            @PathVariable Integer allergeneId) {
        ingredientService.removeAllergeneFromIngredient(ingredientId, allergeneId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{ingredientId}/substances/{substanceId}")
    public ResponseEntity<Void> addSubstanceToIngredient(
            @PathVariable Integer ingredientId,
            @PathVariable Integer substanceId) {
        ingredientService.addSubstanceToIngredient(ingredientId, substanceId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{ingredientId}/substances/{substanceId}")
    public ResponseEntity<Void> removeSubstanceFromIngredient(
            @PathVariable Integer ingredientId,
            @PathVariable Integer substanceId) {
        ingredientService.removeSubstanceFromIngredient(ingredientId, substanceId);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(IngredientNotFoundException.class)
    public ResponseEntity<String> handleIngredientNotFoundException(IngredientNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(SubstanceNociveNotFoundException.class)
    public ResponseEntity<String> handleSubstanceNociveNotFoundException(SubstanceNociveNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
