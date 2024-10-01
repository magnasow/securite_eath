package com.eath.dao;

import com.eath.entite.PasswordResetToken;
import com.eath.entite.Utilisateurs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    Optional<PasswordResetToken> findByToken(String token);
    void deleteByUtilisateur(Utilisateurs utilisateur); // Supprimer le jeton une fois utilisé
}