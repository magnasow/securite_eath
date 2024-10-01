package com.eath.Service.Implement;

import com.eath.dao.UtilisateursRepository;
import com.eath.entite.Utilisateurs;
import com.eath.Service.UtilisateursService;
import com.eath.exception.UtilisateurNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class UtilisateursServiceImpl implements UtilisateursService {

    @Autowired
    private UtilisateursRepository utilisateursRepository;

    @Override
    public Utilisateurs creerUtilisateur(Utilisateurs utilisateur) {
        return utilisateursRepository.save(utilisateur);
    }

    @Override
    public List<Utilisateurs> getAllUtilisateurs() {
        return utilisateursRepository.findAll();
    }

    @Override
    public Optional<Utilisateurs> getUtilisateurById(Integer id) {
        return utilisateursRepository.findById(id);
    }

    @Override
    public Utilisateurs updateUtilisateur(Integer id, Utilisateurs utilisateur) {
        Utilisateurs existingUtilisateur = utilisateursRepository.findById(id)
                .orElseThrow(() -> new UtilisateurNotFoundException("Utilisateur non trouvé avec l'id : " + id));

        existingUtilisateur.setNomPersonne(utilisateur.getNomPersonne());
        existingUtilisateur.setPrenomPersonne(utilisateur.getPrenomPersonne());
        existingUtilisateur.setMotDePasse(utilisateur.getMotDePasse());
        existingUtilisateur.setEmail(utilisateur.getEmail());
        existingUtilisateur.setPhotoDeProfil(utilisateur.getPhotoDeProfil());
        existingUtilisateur.setTaille(utilisateur.getTaille());
        existingUtilisateur.setPoids(utilisateur.getPoids());
        existingUtilisateur.setAge(utilisateur.getAge());
        existingUtilisateur.setSexe(utilisateur.getSexe());
        existingUtilisateur.setConditionsMedicales(utilisateur.getConditionsMedicales());
        existingUtilisateur.setNiveauAbonnement(utilisateur.getNiveauAbonnement());

        // Mise à jour des préférences si elles sont modifiées
        existingUtilisateur.setPreferences(utilisateur.getPreferences());

        return utilisateursRepository.save(existingUtilisateur);
    }

    @Override
    public Optional<Utilisateurs> findByEmail(String email) { // Implémentez la méthode ici
        return utilisateursRepository.findByEmail(email);
    }
}