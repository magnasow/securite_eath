package com.eath.web;

import com.eath.dao.HistoriqueScanRepository;


import com.eath.entite.HistoriqueScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin
@RestController
@RequestMapping("/api/historique-scans")
public class HistoriqueScanController {

    @Autowired
    private HistoriqueScanRepository historiqueScanRepository;

    @GetMapping
    public List<HistoriqueScan> getAllHistoriqueScans() {
        return historiqueScanRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistoriqueScan> getHistoriqueScanById(@PathVariable Integer id) {
        Optional<HistoriqueScan> historiqueScan = historiqueScanRepository.findById(id);
        return historiqueScan.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public HistoriqueScan createHistoriqueScan(@RequestBody HistoriqueScan historiqueScan) {
        return historiqueScanRepository.save(historiqueScan);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HistoriqueScan> updateHistoriqueScan(@PathVariable Integer id, @RequestBody HistoriqueScan historiqueScanDetails) {
        Optional<HistoriqueScan> historiqueScan = historiqueScanRepository.findById(id);
        if (historiqueScan.isPresent()) {
            HistoriqueScan hs = historiqueScan.get();
            hs.setUtilisateur(historiqueScanDetails.getUtilisateur());
            hs.setProduit(historiqueScanDetails.getProduit());
            return ResponseEntity.ok(historiqueScanRepository.save(hs));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHistoriqueScan(@PathVariable Integer id) {
        if (historiqueScanRepository.existsById(id)) {
            historiqueScanRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
