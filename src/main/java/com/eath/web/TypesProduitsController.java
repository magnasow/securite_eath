package com.eath.web;

import com.eath.Service.TypesProduitsService;

import com.eath.entite.TypesProduits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin
@RestController
@RequestMapping("/api/types-produits")
public class TypesProduitsController {

    @Autowired
    private TypesProduitsService typesProduitsService;

    @GetMapping
    public List<TypesProduits> getAllTypesProduits() {
        return typesProduitsService.getAllTypesProduits();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypesProduits> getTypesProduitsById(@PathVariable Integer id) {
        Optional<TypesProduits> typesProduits = typesProduitsService.getTypesProduitsById(id);
        return typesProduits.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public TypesProduits createTypesProduits(@RequestBody TypesProduits typesProduits) {
        return typesProduitsService.createTypesProduits(typesProduits);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TypesProduits> updateTypesProduits(@PathVariable Integer id, @RequestBody TypesProduits typesProduitsDetails) {
        Optional<TypesProduits> updatedTypesProduits = typesProduitsService.updateTypesProduits(id, typesProduitsDetails);
        return updatedTypesProduits.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTypesProduits(@PathVariable Integer id) {
        if (typesProduitsService.deleteTypesProduits(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
