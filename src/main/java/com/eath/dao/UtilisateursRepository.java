package com.eath.dao;

import com.eath.entite.Utilisateurs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateursRepository extends JpaRepository<Utilisateurs, Integer> {
    Optional<Utilisateurs> findByEmail(String email);
}