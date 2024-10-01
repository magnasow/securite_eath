package com.eath.Service.Implement;

import com.eath.Service.IPersonneService;
import com.eath.dao.PersonneRepository;
import com.eath.entite.Personne;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonneServiceImpl implements IPersonneService {

    private final PersonneRepository personneRepository;

    @Override
    public Personne addPersonne(Personne personne) {
        return personneRepository.save(personne);
    }

    @Override
    public List<Personne> getAllPersonnes() {
        return personneRepository.findAll();
    }

    @Override
    public Personne updatePersonne(Integer idPersonne, Personne personne) {
        if (personneRepository.existsById(idPersonne)) {
            personne.setIdPersonne(idPersonne);
            return personneRepository.save(personne);
        } else {
            throw new RuntimeException("Personne not found with id: " + idPersonne);
        }
    }

    @Override
    public void deletePersonne(Integer idPersonne) {
        personneRepository.deleteById(idPersonne);
    }

    @Override
    public Personne getOnePersonne(Integer idPersonne) {
        Optional<Personne> optionalPersonne = personneRepository.findById(idPersonne);
        if (optionalPersonne.isPresent()) {
            return optionalPersonne.get();
        } else {
            throw new RuntimeException("Personne not found with id: " + idPersonne);
        }
    }

    @Override
    public Personne save(Personne personne) {
        return personneRepository.save(personne);
    }
}
