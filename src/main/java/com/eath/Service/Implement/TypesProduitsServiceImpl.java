package com.eath.Service.Implement;

import com.eath.Service.TypesProduitsService;
import com.eath.dao.TypesProduitsRepository;


import com.eath.entite.TypesProduits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class TypesProduitsServiceImpl implements TypesProduitsService {

    @Autowired
    private TypesProduitsRepository repository;

    @Override
    public List<TypesProduits> getAllTypesProduits() {
        return repository.findAll();
    }

    @Override
    public Optional<TypesProduits> getTypesProduitsById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public TypesProduits createTypesProduits(TypesProduits typesProduits) {
        return repository.save(typesProduits);
    }

    @Override
    public Optional<TypesProduits> updateTypesProduits(Integer id, TypesProduits typesProduitsDetails) {
        return repository.findById(id).map(existingTypeProduit -> {
            existingTypeProduit.setNomTypeProduit(typesProduitsDetails.getNomTypeProduit());
            existingTypeProduit.setDescriptionTypeProduit(typesProduitsDetails.getDescriptionTypeProduit());
            existingTypeProduit.setDateModification(Timestamp.from(Instant.now())); // Ensure dateModification is set
            return repository.save(existingTypeProduit);
        });
    }

    @Override
    public boolean deleteTypesProduits(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
