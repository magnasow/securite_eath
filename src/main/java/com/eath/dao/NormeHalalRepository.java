package com.eath.dao;

import com.eath.entite.NormeHalal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NormeHalalRepository extends JpaRepository<NormeHalal, Integer> {
    // Méthodes supplémentaires, si nécessaire
}