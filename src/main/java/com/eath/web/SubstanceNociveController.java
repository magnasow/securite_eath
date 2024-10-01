package com.eath.web;

import com.eath.Service.SubstanceNociveService;
import com.eath.entite.SubstanceNocive;
import com.eath.exception.SubstanceNociveNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/api/substances-nocives")
@RequiredArgsConstructor
@Validated
public class SubstanceNociveController {

    private final SubstanceNociveService substanceNociveService;

    @PostMapping
    public ResponseEntity<SubstanceNocive> createSubstanceNocive(@Valid @RequestBody SubstanceNocive substanceNocive) {
        SubstanceNocive savedSubstanceNocive = substanceNociveService.addSubstanceNocive(substanceNocive);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSubstanceNocive);
    }

    @GetMapping
    public ResponseEntity<List<SubstanceNocive>> getAllSubstancesNocives() {
        List<SubstanceNocive> substancesNocives = substanceNociveService.getAllSubstancesNocives();
        return ResponseEntity.ok(substancesNocives);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubstanceNocive> getSubstanceNociveById(@PathVariable Integer id) {
        SubstanceNocive substanceNocive = substanceNociveService.getSubstanceNociveById(id);
        return ResponseEntity.ok(substanceNocive);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubstanceNocive> updateSubstanceNocive(
            @PathVariable Integer id,
            @Valid @RequestBody SubstanceNocive substanceNocive
    ) {
        SubstanceNocive updatedSubstanceNocive = substanceNociveService.updateSubstanceNocive(id, substanceNocive);
        return ResponseEntity.ok(updatedSubstanceNocive);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubstanceNocive(@PathVariable Integer id) {
        substanceNociveService.deleteSubstanceNocive(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(SubstanceNociveNotFoundException.class)
    public ResponseEntity<String> handleSubstanceNociveNotFoundException(SubstanceNociveNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
