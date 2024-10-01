package com.eath.dao;


import com.eath.entite.TypesProduits;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypesProduitsRepository extends JpaRepository<TypesProduits, Integer> {
}
