package com.eath.dao;

import com.eath.entite.InformationsNutritionnelles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InformationsNutritionnellesRepository extends JpaRepository<InformationsNutritionnelles, Integer> {
}
