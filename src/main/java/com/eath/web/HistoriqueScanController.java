package com.eath.web;

import com.eath.entite.HistoriqueScan;
import com.eath.Service.HistoriqueScanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/historique-scans")
public class HistoriqueScanController {

    @Autowired
    private HistoriqueScanService historiqueScanService;

    // Créer un historique de scan
    @PostMapping
    public ResponseEntity<HistoriqueScan> createHistoriqueScan(@RequestBody HistoriqueScan historiqueScan) {
        HistoriqueScan savedHistoriqueScan = historiqueScanService.enregistrerScan(historiqueScan);
        return ResponseEntity.ok(savedHistoriqueScan);
    }

    // Récupérer tous les historiques de scans
    //@GetMapping
    //public ResponseEntity<Iterable<HistoriqueScan>> getAllHistoriqueScans() {
      //  return ResponseEntity.ok(historiqueScanService.getAllHistoriqueScans());
    //}
}
