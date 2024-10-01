package com.eath.Service;


import com.eath.entite.TypesProduits;

import java.util.List;
import java.util.Optional;

public interface TypesProduitsService {
    List<TypesProduits> getAllTypesProduits();
    Optional<TypesProduits> getTypesProduitsById(Integer id);
    TypesProduits createTypesProduits(TypesProduits typesProduits);
    Optional<TypesProduits> updateTypesProduits(Integer id, TypesProduits typesProduitsDetails);
    boolean deleteTypesProduits(Integer id);
}
