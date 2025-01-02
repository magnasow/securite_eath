package com.eath.web;

import com.eath.dao.ProduitsRepository;
import com.eath.dao.UtilisateursRepository;
import com.eath.entite.HistoriqueScan;
import com.eath.entite.Produits;
import com.eath.entite.Utilisateurs;
import com.eath.service.HistoriqueScanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/historique-scans")
public class HistoriqueScanController {
    private static final Logger logger = LoggerFactory.getLogger(HistoriqueScanController.class);

    @Autowired
    private HistoriqueScanService historiqueScanService;
    @Autowired
    private ProduitsRepository produitsRepository;
    @Autowired
    private UtilisateursRepository utilisateursRepository;
    @PostMapping("/create")
    public ResponseEntity<String> createHistoriqueScan(@RequestBody HistoriqueScan historiqueScan) {
        logger.debug("Requête reçue : {}", historiqueScan);

        if (historiqueScan.getUtilisateur() == null || historiqueScan.getUtilisateur().getIdPersonne() == null) {
            logger.debug("L'utilisateur ou l'ID utilisateur est manquant.");
            return ResponseEntity.badRequest().body("L'ID de l'utilisateur est requis.");
        }

        // Logique pour traiter la requête...
        return ResponseEntity.ok("Historique de scan créé avec succès.");
    }

}
