package com.eath.web;

import com.eath.Service.RecetteService;
import com.eath.entite.Recette;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/recettes")
public class RecetteController {

    private final RecetteService recetteService;

    @Autowired
    public RecetteController(RecetteService recetteService) {
        this.recetteService = recetteService;
    }

    // Endpoint pour obtenir les recettes avec un paramètre lang
    @GetMapping
    public List<Recette> getAllRecettes(@RequestParam("lang") String lang) {
        return recetteService.getRecettesByLanguage(lang);
    }
}
