package com.eath.web;

import com.eath.Service.NutritionService;
import com.eath.entite.Nutrition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/nutritions")
public class NutritionController {

    @Autowired
    private NutritionService nutritionService;

    @GetMapping
    public List<Nutrition> getNutritions(@RequestParam(value = "lang", required = false, defaultValue = "fr") String lang) {
        return nutritionService.getNutritions().stream()
                .map(nutrition -> new Nutrition(
                        nutrition.getImage(),
                        Map.of(lang, nutrition.getTitre().getOrDefault(lang, nutrition.getTitre().get("fr"))),
                        Map.of(lang, nutrition.getDescription().getOrDefault(lang, nutrition.getDescription().get("fr"))),
                        nutrition.getAuteur(),
                        nutrition.getDate()
                ))
                .collect(Collectors.toList());
    }
}
