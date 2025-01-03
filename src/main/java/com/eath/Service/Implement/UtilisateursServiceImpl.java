package com.eath.Service.Implement;

import com.eath.dao.UtilisateursRepository;
import com.eath.entite.Utilisateurs;
import com.eath.Service.UtilisateursService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
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
        return utilisateursRepository.findById(id); // Returns an Optional<Utilisateurs>
    }


    @Override
    public Utilisateurs updateUtilisateur(Integer id, Utilisateurs utilisateur) {
        Utilisateurs existingUtilisateur = utilisateursRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Utilisateur avec l'ID " + id + " non trouvé."));

        // Mettre à jour les autres informations utilisateur sans toucher au mot de passe
        BeanUtils.copyProperties(utilisateur, existingUtilisateur, "idPersonne", "motDePasse");

        return utilisateursRepository.save(existingUtilisateur);
    }


    @Override
    public Optional<Utilisateurs> findByEmail(String email) {
        return utilisateursRepository.findByEmail(email);
    }

    @Override
    public boolean deleteUtilisateur(Integer id) {
        if (utilisateursRepository.existsById(id)) {
            utilisateursRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Utilisateurs saveUtilisateur(Utilisateurs utilisateur) {
        return utilisateursRepository.save(utilisateur);
    }
}
