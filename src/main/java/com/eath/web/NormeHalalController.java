package com.eath.web;

import com.eath.Service.NormeHalalService;
import com.eath.entite.NormeHalal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/api/normes-halal")
@RequiredArgsConstructor
public class NormeHalalController {

    private final NormeHalalService normeHalalService;

    @GetMapping
    public List<NormeHalal> getAllNormeHalal() {
        return normeHalalService.getAllNormeHalal();
    }

    @GetMapping("/{id}")
    public ResponseEntity<NormeHalal> getNormeHalalById(@PathVariable Integer id) {
        NormeHalal normeHalal = normeHalalService.getNormeHalalById(id);
        return ResponseEntity.ok(normeHalal);
    }

    @PostMapping
    public ResponseEntity<NormeHalal> createNormeHalal(@RequestBody NormeHalal normeHalal) {
        NormeHalal createdNormeHalal = normeHalalService.addNormeHalal(normeHalal);
        return ResponseEntity.ok(createdNormeHalal);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NormeHalal> updateNormeHalal(@PathVariable Integer id, @RequestBody NormeHalal normeHalal) {
        NormeHalal updatedNormeHalal = normeHalalService.updateNormeHalal(id, normeHalal);
        return ResponseEntity.ok(updatedNormeHalal);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNormeHalal(@PathVariable Integer id) {
        normeHalalService.deleteNormeHalal(id);
        return ResponseEntity.noContent().build();
    }
}
