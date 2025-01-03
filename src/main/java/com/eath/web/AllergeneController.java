package com.eath.web;

import com.eath.entite.Allergene;
import com.eath.entite.Utilisateurs;
import com.eath.Service.AllergeneService;
import com.eath.Service.UtilisateursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/allergenes")
public class AllergeneController {

    @Autowired
    private AllergeneService allergeneService;

    @Autowired
    private UtilisateursService utilisateursService;

    @Transactional
    @PostMapping("/utilisateurs/{id}")
    public ResponseEntity<Map<String, Object>> addAllergenesToUser(
            @PathVariable Integer id,
            @RequestBody AllergeneRequest requestBody
    ) {
        Optional<Utilisateurs> utilisateurOpt = utilisateursService.getUtilisateurById(id);

        if (utilisateurOpt.isEmpty()) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Utilisateur non trouvé");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        Utilisateurs utilisateur = utilisateurOpt.get();
        List<String> nomAllergenes = requestBody.getNomAllergenes();

        if (nomAllergenes == null || nomAllergenes.isEmpty()) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Aucun allergène fourni");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        List<Allergene> allergenes = allergeneService.getAllergenesByNames(nomAllergenes);
        if (allergenes.isEmpty()) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Aucun allergène trouvé pour les noms donnés");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        // Initialisez les relations chargées paresseusement, comme les ingrédients
        allergenes.forEach(allergene -> {
            allergene.getIngredients().size(); // Force l'initialisation des ingrédients
        });

        utilisateur.getAllergenes().addAll(allergenes);
        utilisateursService.saveUtilisateur(utilisateur);

        // Convertissez les allergènes en DTOs avant de renvoyer la réponse
        List<Map<String, Object>> allergeneDTOs = allergenes.stream()
                .map(allergene -> {
                    Map<String, Object> allergeneMap = new HashMap<>();
                    allergeneMap.put("id", allergene.getId());
                    allergeneMap.put("nomAllergenes", allergene.getNomAllergenes());
                    allergeneMap.put("descriptionAllergene", allergene.getDescriptionAllergene());
                    return allergeneMap;
                })
                .collect(Collectors.toList());

        // Retournez un message de succès et les allergènes ajoutés
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Les allergènes ont été ajoutés avec succès");
        response.put("allergenes", allergeneDTOs);
        return ResponseEntity.ok(response);
    }
}
