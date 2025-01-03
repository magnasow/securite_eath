package com.eath.web;

import com.eath.Service.HistoriqueScanService;
import com.eath.Service.InformationsNutritionnellesService;
import com.eath.entite.HistoriqueScan;
import com.eath.entite.InformationsNutritionnelles;
import com.eath.entite.Produits;
import com.eath.Service.UtilisateursService;
import com.eath.Service.IProduitsService;
import com.eath.entite.Utilisateurs;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/historique-scans")
@RequiredArgsConstructor
public class HistoriqueScanController {

    private final HistoriqueScanService historiqueScanService;
    private final UtilisateursService utilisateursService;
    private final IProduitsService produitsService;
    private final InformationsNutritionnellesService informationNutritionnelleService; // Service pour récupérer les données nutritionnelles

    @PostMapping
    public ResponseEntity<HistoriqueScanDTO> createHistoriqueScan(@RequestBody HistoriqueScan historiqueScan) {
        Utilisateurs utilisateur = utilisateursService.findById(historiqueScan.getUtilisateur().getIdPersonne())
                .orElseThrow(() -> new RuntimeException("Utilisateur not found"));
        Produits produit = produitsService.findById(historiqueScan.getProduit().getIdProduit())
                .orElseThrow(() -> new RuntimeException("Produit not found"));

        // Récupération des informations nutritionnelles du produit
        InformationsNutritionnelles informationNutritionnelle = informationNutritionnelleService
                .findByProduit(produit)
                .orElseThrow(() -> new RuntimeException("Information nutritionnelle not found"));

        HistoriqueScan savedHistoriqueScan = historiqueScanService.enregistrerScan(historiqueScan);

        // Créer le DTO avec toutes les informations
        HistoriqueScanDTO historiqueScanDTO = new HistoriqueScanDTO();
        historiqueScanDTO.setIdHistoriqueScan(savedHistoriqueScan.getIdHistoriqueScan());
        historiqueScanDTO.setNomProduit(produit.getNomProduit());
        historiqueScanDTO.setNormeHalal(produit.getNormeHalal().getDescriptionNormeHalal());

        // Remplir les informations nutritionnelles dans le DTO
        InformationNutritionnelleDTO nutritionDTO = new InformationNutritionnelleDTO();
        nutritionDTO.setIdInformation(informationNutritionnelle.getIdInformation());
        nutritionDTO.setCalories(informationNutritionnelle.getCalories());
        nutritionDTO.setGlucides(informationNutritionnelle.getGlucides());
        nutritionDTO.setGraisses(informationNutritionnelle.getGraisses());
        nutritionDTO.setProteines(informationNutritionnelle.getProteines());
        nutritionDTO.setDateCreation(informationNutritionnelle.getDateCreation());
        nutritionDTO.setDateModification(informationNutritionnelle.getDateModification());

        historiqueScanDTO.setInformationNutritionnelle(nutritionDTO);

        return ResponseEntity.ok(historiqueScanDTO);
    }
}

