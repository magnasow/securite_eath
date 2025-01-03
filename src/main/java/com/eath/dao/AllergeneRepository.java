package com.eath.dao;

import com.eath.entite.Allergene;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AllergeneRepository extends JpaRepository<Allergene, Integer> {

    // Recherche des allergènes par nom
    List<Allergene> findByNomAllergenesIn(List<String> nomAllergenes);
}
