package com.eath.web;

import com.eath.Service.AllergeneService;
import com.eath.entite.Allergene;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/allergenes")
public class AllergeneController {

    @Autowired
    private AllergeneService allergeneService;

    // Récupérer tous les allergènes
    @GetMapping
    public List<Allergene> getAllAllergenes() {
        return allergeneService.getAllAllergenes();
    }

    // Récupérer un allergène par son ID
    @GetMapping("/{id}")
    public Allergene getAllergeneById(@PathVariable int id) {
        return allergeneService.getAllergeneById(id).orElse(null);
    }

    // Créer un allergène
    @PostMapping
    public Allergene createAllergene(@RequestBody Allergene allergene) {
        return allergeneService.saveAllergene(allergene);
    }

    // Supprimer un allergène
    @DeleteMapping("/{id}")
    public void deleteAllergene(@PathVariable int id) {
        allergeneService.deleteAllergene(id);
    }

    // Mettre à jour un allergène
    @PutMapping("/{id}")
    public Allergene updateAllergene(@PathVariable int id, @RequestBody Allergene allergeneDetails) {
        return allergeneService.updateAllergene(id, allergeneDetails);
    }
}
