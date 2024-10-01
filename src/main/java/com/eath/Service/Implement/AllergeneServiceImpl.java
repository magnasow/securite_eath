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

    @Override
    public List<Allergene> getAllAllergenes() {
        return allergeneRepository.findAll();
    }

    @Override
    public Optional<Allergene> getAllergeneById(int id) {
        return allergeneRepository.findById(id);
    }

    @Override
    public Allergene saveAllergene(Allergene allergene) {
        return allergeneRepository.save(allergene);
    }

    @Override
    public void deleteAllergene(int id) {
        allergeneRepository.deleteById(id);
    }

    @Override
    public Allergene updateAllergene(int id, Allergene allergeneDetails) {
        Optional<Allergene> optionalAllergene = allergeneRepository.findById(id);

        if (optionalAllergene.isPresent()) {
            Allergene existingAllergene = optionalAllergene.get();
            existingAllergene.setNomAllergene(allergeneDetails.getNomAllergene());
            existingAllergene.setDescriptionAllergene(allergeneDetails.getDescriptionAllergene());
            // dateModification will be automatically updated by @PreUpdate in the entity
            return allergeneRepository.save(existingAllergene);
        } else {
            throw new RuntimeException("Allergene not found with id " + id);
        }
    }
}
