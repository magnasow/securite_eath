package com.eath.web;

import com.eath.Service.IProduitsService;
import com.eath.entite.Produits;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/produits")
public class ProduitsController {
    private final IProduitsService produitsService;

    @PostMapping
    public ResponseEntity<Produits> addProduits(@RequestBody Produits produits) {
        try {
            Produits createdProduit = produitsService.addProduits(produits);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdProduit);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<Produits>> listProduits() {
        return ResponseEntity.ok(produitsService.getAllProduits());
    }

    @PutMapping("/{idProduit}")
    public ResponseEntity<Produits> updateProduits(@PathVariable Integer idProduit, @RequestBody Produits produits) {
        try {
            Produits updatedProduit = produitsService.updateProduits(idProduit, produits);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedProduit);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/{idProduit}")
    public ResponseEntity<Produits> getOneProduits(@PathVariable Integer idProduit) {
        try {
            Produits produit = produitsService.getOneProduits(idProduit);
            return ResponseEntity.ok(produit);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
