package com.eath.web;

import com.eath.dao.HistoriqueScanRepository;
import com.eath.dao.UtilisateursRepository;
import com.eath.dao.ProduitsRepository;
import com.eath.entite.HistoriqueScan;
import com.eath.entite.Utilisateurs;
import com.eath.entite.Produits;
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

    @Autowired
    private UtilisateursRepository utilisateurRepository;

    @Autowired
    private ProduitsRepository produitRepository;

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
    public ResponseEntity<?> createHistoriqueScan(@RequestBody HistoriqueScan historiqueScan) {
        // Vérification de l'utilisateur avant de l'associer
        Optional<Utilisateurs> utilisateur = utilisateurRepository.findById(historiqueScan.getUtilisateur().getIdPersonne());
        if (utilisateur.isEmpty()) {
            return ResponseEntity.badRequest().body("Utilisateur non trouvé avec l'ID: " + historiqueScan.getUtilisateur().getIdPersonne());
        }
        historiqueScan.setUtilisateur(utilisateur.get());

        // Vérification du produit avant de l'associer
        Optional<Produits> produit = produitRepository.findById(historiqueScan.getProduit().getIdProduit());
        if (produit.isEmpty()) {
            return ResponseEntity.badRequest().body("Produit non trouvé avec l'ID: " + historiqueScan.getProduit().getIdProduit());
        }
        historiqueScan.setProduit(produit.get());

        // Sauvegarder l'historique
        HistoriqueScan savedScan = historiqueScanRepository.save(historiqueScan);
        return ResponseEntity.ok(savedScan);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HistoriqueScan> updateHistoriqueScan(@PathVariable Integer id, @RequestBody HistoriqueScan historiqueScanDetails) {
        Optional<HistoriqueScan> historiqueScan = historiqueScanRepository.findById(id);
        if (historiqueScan.isPresent()) {
            HistoriqueScan hs = historiqueScan.get();

            // Vérification des nouvelles entités associées (utilisateur et produit)
            Optional<Utilisateurs> utilisateur = utilisateurRepository.findById(historiqueScanDetails.getUtilisateur().getIdPersonne());
            Optional<Produits> produit = produitRepository.findById(historiqueScanDetails.getProduit().getIdProduit());

            if (utilisateur.isPresent() && produit.isPresent()) {
                hs.setUtilisateur(utilisateur.get());
                hs.setProduit(produit.get());

                // Sauvegarde des modifications
                HistoriqueScan updatedScan = historiqueScanRepository.save(hs);
                return ResponseEntity.ok(updatedScan);
            } else {
                return ResponseEntity.badRequest().body(null);  // Retourner un message d'erreur approprié
            }
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
