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
    public ResponseEntity<InformationsNutritionnelles> createInformationsNutritionnelles(@Valid @RequestBody InformationsNutritionnelles info) {
        InformationsNutritionnelles savedInfo = informationsNutritionnellesService.addInformationsNutritionnelles(info);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedInfo);
    }

    @GetMapping
    public ResponseEntity<List<InformationsNutritionnelles>> getAllInformationsNutritionnelles() {
        List<InformationsNutritionnelles> infos = informationsNutritionnellesService.getAllInformationsNutritionnelles();
        return ResponseEntity.ok(infos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InformationsNutritionnelles> getInformationsNutritionnellesById(@PathVariable Integer id) {
        InformationsNutritionnelles info = informationsNutritionnellesService.getInformationsNutritionnellesById(id);
        return ResponseEntity.ok(info);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InformationsNutritionnelles> updateInformationsNutritionnelles(
            @PathVariable Integer id,
            @Valid @RequestBody InformationsNutritionnelles info
    ) {
        InformationsNutritionnelles updatedInfo = informationsNutritionnellesService.updateInformationsNutritionnelles(id, info);
        return ResponseEntity.ok(updatedInfo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInformationsNutritionnelles(@PathVariable Integer id) {
        informationsNutritionnellesService.deleteInformationsNutritionnelles(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(InformationsNutritionnellesNotFoundException.class)
    public ResponseEntity<String> handleInformationsNutritionnellesNotFoundException(InformationsNutritionnellesNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
