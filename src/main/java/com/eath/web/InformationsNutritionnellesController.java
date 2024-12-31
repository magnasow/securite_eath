package com.eath.web;

import com.eath.Service.InformationsNutritionnellesService;
import com.eath.entite.InformationsNutritionnelles;
import com.eath.exception.InformationsNutritionnellesNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/informations-nutritionnelles")
@RequiredArgsConstructor
@Validated
public class InformationsNutritionnellesController {

    private final InformationsNutritionnellesService informationsNutritionnellesService;

    @PostMapping
    public ResponseEntity<InformationsNutritionnelles> addInformationsNutritionnellesWithCodeBarre(
            @RequestParam String codeBarre,
            @RequestBody InformationsNutritionnelles infoNut) {

        try {
            InformationsNutritionnelles createdInfo = informationsNutritionnellesService.addInformationsNutritionnellesWithCodeBarre(codeBarre, infoNut);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdInfo);
        } catch (RuntimeException e) {
            // Gérer l'erreur de produit non trouvé ou tout autre type d'exception
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            // Pour toutes les autres exceptions générales
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null); // Vous pouvez ajouter un message d'erreur personnalisé dans le corps de la réponse
        }
    }


    @GetMapping
    public ResponseEntity<List<InformationsNutritionnelles>> getAllInformationsNutritionnelles() {
        List<InformationsNutritionnelles> infos = informationsNutritionnellesService.getAllInformationsNutritionnelles();
        return ResponseEntity.ok(infos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InformationsNutritionnelles> getInformationsNutritionnellesById(@PathVariable Integer id) {
        try {
            InformationsNutritionnelles info = informationsNutritionnellesService.getInformationsNutritionnellesById(id);
            return ResponseEntity.ok(info);
        } catch (InformationsNutritionnellesNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<InformationsNutritionnelles> updateInformationsNutritionnelles(
            @PathVariable Integer id,
            @Valid @RequestBody InformationsNutritionnelles info
    ) {
        try {
            InformationsNutritionnelles updatedInfo = informationsNutritionnellesService.updateInformationsNutritionnelles(id, info);
            return ResponseEntity.ok(updatedInfo);
        } catch (InformationsNutritionnellesNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInformationsNutritionnelles(@PathVariable Integer id) {
        try {
            informationsNutritionnellesService.deleteInformationsNutritionnelles(id);
            return ResponseEntity.noContent().build();
        } catch (InformationsNutritionnellesNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @ExceptionHandler(InformationsNutritionnellesNotFoundException.class)
    public ResponseEntity<String> handleInformationsNutritionnellesNotFoundException(InformationsNutritionnellesNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
