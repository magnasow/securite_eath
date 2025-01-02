package com.eath.dao;

import com.eath.entite.InformationsNutritionnelles;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface InformationsNutritionnellesRepository extends JpaRepository<InformationsNutritionnelles, Integer> {
    // Change to use produit.idProduit
    Optional<InformationsNutritionnelles> findByProduit_IdProduit(Integer idProduit);
}
