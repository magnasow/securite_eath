package com.eath.Service.Implement;

import com.eath.entite.HistoriqueScan;
import com.eath.entite.Utilisateurs;
import com.eath.dao.HistoriqueScanRepository;
import com.eath.dao.UtilisateursRepository;
import com.eath.service.HistoriqueScanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class HistoriqueScanServiceImpl implements HistoriqueScanService {

    @Autowired
    private UtilisateursRepository utilisateurRepository;

    @Autowired
    private HistoriqueScanRepository historiqueScanRepository;


    @Override
    public HistoriqueScan createHistoriqueScan(HistoriqueScan historiqueScan) {
        if (historiqueScan.getUtilisateur() == null || historiqueScan.getUtilisateur().getIdPersonne() == null) {
            throw new IllegalArgumentException("L'ID de l'utilisateur est requis.");
        }

        // Récupérer l'utilisateur
        Utilisateurs utilisateur = utilisateurRepository.findById(historiqueScan.getUtilisateur().getIdPersonne())
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé"));

        // Affecter l'utilisateur à l'historique
        historiqueScan.setUtilisateur(utilisateur);

        // Sauvegarder l'historique de scan
        return historiqueScanRepository.save(historiqueScan);
    }
}
