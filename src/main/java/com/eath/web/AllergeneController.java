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

    @GetMapping
    public List<Allergene> getAllAllergenes() {
        return allergeneService.getAllAllergenes();
    }

    @GetMapping("/{id}")
    public Allergene getAllergeneById(@PathVariable int id) {
        return allergeneService.getAllergeneById(id).orElse(null);
    }

    @PostMapping
    public Allergene createAllergene(@RequestBody Allergene allergene) {
        return allergeneService.saveAllergene(allergene);
    }

    @DeleteMapping("/{id}")
    public void deleteAllergene(@PathVariable int id) {
        allergeneService.deleteAllergene(id);
    }

    @PutMapping("/{id}")
    public Allergene updateAllergene(@PathVariable int id, @RequestBody Allergene allergeneDetails) {
        return allergeneService.updateAllergene(id, allergeneDetails);
    }
}
