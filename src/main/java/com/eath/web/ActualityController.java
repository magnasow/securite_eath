package com.eath.web;

import com.eath.Service.ActualityService;
import com.eath.entite.Actuality;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/actualites")
public class ActualityController {
    @Autowired
    private ActualityService actualityService;

    @GetMapping
    public List<Actuality> getActualites(@RequestParam(value = "lang", required = false, defaultValue = "fr") String lang) {
        // Récupère la liste complète des actualités
        List<Actuality> actualites = actualityService.getActualites();

        // Filtre les titres et descriptions pour la langue spécifiée
        return actualites.stream().map(actuality -> {
            Actuality filteredActuality = new Actuality(
                    actuality.getImage(),
                    actuality.getTitres().entrySet().stream()
                            .filter(entry -> entry.getKey().equals(lang))
                            .collect(Collectors.toMap(
                                    entry -> entry.getKey(),
                                    entry -> entry.getValue())),
                    actuality.getDescriptions().entrySet().stream()
                            .filter(entry -> entry.getKey().equals(lang))
                            .collect(Collectors.toMap(
                                    entry -> entry.getKey(),
                                    entry -> entry.getValue())),
                    actuality.getAuteur(),
                    actuality.getDate()
            );
            return filteredActuality;
        }).collect(Collectors.toList());
    }
}
