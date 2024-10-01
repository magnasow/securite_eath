package com.eath.Service;

import com.eath.entite.Utilisateurs;

import java.util.List;
import java.util.Optional;

public interface UtilisateursService {
    Utilisateurs creerUtilisateur(Utilisateurs utilisateur);
    List<Utilisateurs> getAllUtilisateurs();
    Optional<Utilisateurs> getUtilisateurById(Integer id);
    Utilisateurs updateUtilisateur(Integer id, Utilisateurs utilisateur); // Conserver la signature avec l'ID
    Optional<Utilisateurs> findByEmail(String email);
}
