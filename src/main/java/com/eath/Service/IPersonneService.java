package com.eath.Service;

import com.eath.entite.Personne;
import java.util.List;

public interface IPersonneService {
    Personne addPersonne(Personne personne);
    List<Personne> getAllPersonnes();
    Personne updatePersonne(Integer idPersonne, Personne personne);
    void deletePersonne(Integer idPersonne);
    Personne getOnePersonne(Integer idPersonne);
    Personne save(Personne personne);
}
