package com.eath.web;

import com.eath.Service.ResultatAnalyseService;
import com.eath.entite.ResultatAnalyse;
import com.eath.exception.ResultatAnalyseNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/api/resultats-analyse")
@RequiredArgsConstructor
@Validated
public class ResultatAnalyseController {

    private final ResultatAnalyseService resultatAnalyseService;

    @PostMapping
    public ResponseEntity<ResultatAnalyse> createResultatAnalyse(@Valid @RequestBody ResultatAnalyse resultatAnalyse) {
        ResultatAnalyse savedResultatAnalyse = resultatAnalyseService.addResultatAnalyse(resultatAnalyse);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedResultatAnalyse);
    }

    @GetMapping
    public ResponseEntity<List<ResultatAnalyse>> getAllResultatsAnalyse() {
        List<ResultatAnalyse> resultatsAnalyse = resultatAnalyseService.getAllResultatsAnalyse();
        return ResponseEntity.ok(resultatsAnalyse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResultatAnalyse> getResultatAnalyseById(@PathVariable Integer id) {
        ResultatAnalyse resultatAnalyse = resultatAnalyseService.getResultatAnalyseById(id);
        return ResponseEntity.ok(resultatAnalyse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResultatAnalyse> updateResultatAnalyse(
            @PathVariable Integer id,
            @Valid @RequestBody ResultatAnalyse resultatAnalyse
    ) {
        ResultatAnalyse updatedResultatAnalyse = resultatAnalyseService.updateResultatAnalyse(id, resultatAnalyse);
        return ResponseEntity.ok(updatedResultatAnalyse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResultatAnalyse(@PathVariable Integer id) {
        resultatAnalyseService.deleteResultatAnalyse(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(ResultatAnalyseNotFoundException.class)
    public ResponseEntity<String> handleResultatAnalyseNotFoundException(ResultatAnalyseNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
