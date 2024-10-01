package com.eath.web;

import com.eath.Service.ProduitIngredientService;
import com.eath.dao.IngredientRepository;
import com.eath.dao.ProduitsRepository;
import com.eath.entite.Ingredient;
import com.eath.entite.ProduitIngredient;
import com.eath.entite.ProduitIngredientKey;
import com.eath.entite.Produits;
import com.eath.exception.ProduitIngredientNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/produits-ingredients")
@RequiredArgsConstructor
public class ProduitIngredientController {

    private final ProduitIngredientService produitIngredientService;
    private final IngredientRepository IngredientRepository;
    private final ProduitsRepository ProduitsRepository;

    @PostMapping
    public ResponseEntity<ProduitIngredient> addProduitIngredient(@RequestBody ProduitIngredient produitIngredient) {
        try {
            // Vérifier si l'objet ProduitIngredientKey existe
            ProduitIngredientKey id = produitIngredient.getId();
            if (id == null || id.getIdProduit() == null || id.getIdIngredient() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }

            // Récupérer l'objet Ingredient
            Ingredient ingredient = IngredientRepository.findById(id.getIdIngredient())
                    .orElseThrow(() -> new ProduitIngredientNotFoundException("Ingredient not found"));

            // Récupérer l'objet Produit
            Produits produit = ProduitsRepository.findById(id.getIdProduit())
                    .orElseThrow(() -> new ProduitIngredientNotFoundException("Produit not found"));

            // Associer l'ingrédient et le produit à l'objet ProduitIngredient
            produitIngredient.setIngredient(ingredient);
            produitIngredient.setProduit(produit);

            // Sauvegarder le produit-ingredient
            ProduitIngredient createdProduitIngredient = produitIngredientService.addProduitIngredient(produitIngredient);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdProduitIngredient);
        } catch (ProduitIngredientNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            e.printStackTrace(); // Log de l'erreur pour plus de détails
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<ProduitIngredient>> getAllProduitIngredients() {
        try {
            return ResponseEntity.ok(produitIngredientService.getAllProduitIngredients());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{idProduit}/{idIngredient}")
    public ResponseEntity<ProduitIngredient> getProduitIngredientById(@PathVariable Integer idProduit, @PathVariable Integer idIngredient) {
        try {
            ProduitIngredientKey id = new ProduitIngredientKey(idProduit, idIngredient);
            ProduitIngredient produitIngredient = produitIngredientService.getProduitIngredientById(id);
            return produitIngredient != null ? ResponseEntity.ok(produitIngredient) : ResponseEntity.notFound().build();
        } catch (ProduitIngredientNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/{idProduit}/{idIngredient}")
    public ResponseEntity<ProduitIngredient> updateProduitIngredient(@PathVariable Integer idProduit,
                                                                     @PathVariable Integer idIngredient,
                                                                     @RequestBody ProduitIngredient produitIngredient) {
        try {
            ProduitIngredientKey id = new ProduitIngredientKey(idProduit, idIngredient);
            ProduitIngredient updatedProduitIngredient = produitIngredientService.updateProduitIngredient(id, produitIngredient);
            return ResponseEntity.ok(updatedProduitIngredient);
        } catch (ProduitIngredientNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{idProduit}/{idIngredient}")
    public ResponseEntity<Void> deleteProduitIngredient(@PathVariable Integer idProduit, @PathVariable Integer idIngredient) {
        try {
            ProduitIngredientKey id = new ProduitIngredientKey(idProduit, idIngredient);
            produitIngredientService.deleteProduitIngredient(id);
            return ResponseEntity.noContent().build();
        } catch (ProduitIngredientNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
