package com.eath.dao;


import com.eath.entite.Produits;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitsRepository extends JpaRepository<Produits, Integer> {
}
