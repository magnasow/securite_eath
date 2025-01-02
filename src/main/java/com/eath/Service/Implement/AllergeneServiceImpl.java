package com.eath.Service.Implement;

import com.eath.Service.AllergeneService;
import com.eath.dao.AllergeneRepository;
import com.eath.entite.Allergene;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AllergeneServiceImpl implements AllergeneService {

    @Autowired
    private AllergeneRepository allergeneRepository;

    // Récupérer tous les allergènes
    @Override
    public List<Allergene> getAllAllergenes() {
        return allergeneRepository.findAll();
    }

    // Récupérer un allergène par son ID
    @Override
    public Optional<Allergene> getAllergeneById(int id) {
        return allergeneRepository.findById(id);
    }

    // Créer un allergène
    @Override
    public Allergene saveAllergene(Allergene allergene) {
        return allergeneRepository.save(allergene);
    }

    // Supprimer un allergène
    @Override
    public void deleteAllergene(int id) {
        allergeneRepository.deleteById(id);
    }

    // Mettre à jour un allergène
    @Override
    public Allergene updateAllergene(int id, Allergene allergeneDetails) {
        Optional<Allergene> optionalAllergene = allergeneRepository.findById(id);

        if (optionalAllergene.isPresent()) {
            Allergene existingAllergene = optionalAllergene.get();

            // Mise à jour des noms d'allergènes (Liste de String)
            existingAllergene.setNomAllergenes(allergeneDetails.getNomAllergenes());
            existingAllergene.setDescriptionAllergene(allergeneDetails.getDescriptionAllergene());

            // La date de modification sera automatiquement mise à jour grâce à @PreUpdate dans l'entité
            return allergeneRepository.save(existingAllergene);
        } else {
            throw new RuntimeException("Allergene not found with id " + id);
        }
    }
}
