package com.eath.dao;

import com.eath.entite.Produits;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

public interface ProduitsRepository extends JpaRepository<Produits, Integer> {
    Optional<Produits> findByCodeBarre(String codeBarre);
}
