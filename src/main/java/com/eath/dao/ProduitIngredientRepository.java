package com.eath.dao;

import com.eath.entite.ProduitIngredient;
import com.eath.entite.ProduitIngredientKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProduitIngredientRepository extends JpaRepository<ProduitIngredient, ProduitIngredientKey> {
}
