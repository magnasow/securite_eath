package com.eath.Service.Implement;

import com.eath.Service.HistoriqueScanService;
import com.eath.dao.HistoriqueScanRepository;
import com.eath.dao.UtilisateursRepository;
import com.eath.dao.ProduitsRepository;
import com.eath.entite.HistoriqueScan;
import com.eath.entite.Produits;
import com.eath.entite.Utilisateurs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class HistoriqueScanServiceImpl implements HistoriqueScanService {

    @Autowired
    private HistoriqueScanRepository historiqueScanRepository;

    @Autowired
    private UtilisateursRepository utilisateursRepository;

    @Autowired
    private ProduitsRepository produitRepository;

    // Méthode privée pour récupérer un Utilisateur par ID
    private Utilisateurs getUtilisateurById(Integer id) {
        return utilisateursRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Utilisateur non trouvé."));
    }

    // Méthode privée pour récupérer un Produit par ID
    private Produits getProduitById(Integer id) {
        return produitRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produit non trouvé."));
    }

    @Override
    public List<HistoriqueScan> getAllHistoriqueScans() {
        return historiqueScanRepository.findAll();
    }

    @Override
    public Optional<HistoriqueScan> getHistoriqueScanById(Integer id) {
        return historiqueScanRepository.findById(id);
    }

    @Override
    public HistoriqueScan createHistoriqueScan(HistoriqueScan historiqueScan) {
        // Vérification si l'utilisateur et le produit sont initialisés
        if (historiqueScan.getUtilisateur() == null || historiqueScan.getProduit() == null) {
            throw new IllegalArgumentException("Utilisateur ou Produit manquant.");
        }

        // Initialiser l'utilisateur et le produit à partir de la base de données
        Utilisateurs utilisateur = utilisateursRepository.findById(historiqueScan.getUtilisateur().getIdPersonne())
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé."));

        Produits produit = produitRepository.findById(historiqueScan.getProduit().getIdProduit())
                .orElseThrow(() -> new IllegalArgumentException("Produit non trouvé."));

        // Assigner les entités récupérées à l'historique scan
        historiqueScan.setUtilisateur(utilisateur);
        historiqueScan.setProduit(produit);

        // Sauvegarder l'historique scan dans la base de données
        return historiqueScanRepository.save(historiqueScan);
    }


    @Override
    public Optional<HistoriqueScan> updateHistoriqueScan(Integer id, HistoriqueScan historiqueScanDetails) {
        Optional<HistoriqueScan> historiqueScanOpt = historiqueScanRepository.findById(id);
        if (historiqueScanOpt.isPresent()) {
            HistoriqueScan hs = historiqueScanOpt.get();

            // Vérification si l'utilisateur est initialisé
            if (historiqueScanDetails.getUtilisateur() != null && historiqueScanDetails.getUtilisateur().getIdPersonne() != null) {
                Utilisateurs utilisateur = utilisateursRepository.findById(historiqueScanDetails.getUtilisateur().getIdPersonne())
                        .orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé."));
                hs.setUtilisateur(utilisateur);
            } else {
                throw new IllegalArgumentException("Utilisateur non initialisé.");
            }

            // Vérification si le produit est initialisé
            if (historiqueScanDetails.getProduit() != null && historiqueScanDetails.getProduit().getIdProduit() != null) {
                Produits produit = produitRepository.findById(historiqueScanDetails.getProduit().getIdProduit())
                        .orElseThrow(() -> new IllegalArgumentException("Produit non trouvé."));
                hs.setProduit(produit);
            } else {
                throw new IllegalArgumentException("Produit non initialisé.");
            }

            // Mettre à jour la date de scan
            hs.setDateScan(historiqueScanDetails.getDateScan());

            // Sauvegarder la mise à jour dans la base de données
            return Optional.of(historiqueScanRepository.save(hs));
        } else {
            return Optional.empty(); // Retourner Optional.empty() si l'historique scan n'existe pas
        }
    }


    @Override
    public boolean deleteHistoriqueScan(Integer id) {
        if (historiqueScanRepository.existsById(id)) {
            historiqueScanRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
