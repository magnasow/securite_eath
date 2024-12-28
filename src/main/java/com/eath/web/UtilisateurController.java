package com.eath.web;

import com.eath.Service.UtilisateursService;
import com.eath.entite.Utilisateurs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

    @Autowired
    private UtilisateursService utilisateursService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Récupérer tous les utilisateurs
    @GetMapping
    public ResponseEntity<List<Utilisateurs>> getAllUtilisateurs() {
        List<Utilisateurs> utilisateurs = utilisateursService.getAllUtilisateurs();
        return ResponseEntity.ok(utilisateurs);
    }

    // Récupérer un utilisateur par son ID
    @GetMapping("/{id}")
    public ResponseEntity<Utilisateurs> getUtilisateurById(@PathVariable("id") Integer id) { // Id de type Integer
        Optional<Utilisateurs> utilisateur = utilisateursService.getUtilisateurById(id);
        return utilisateur.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Mettre à jour un utilisateur existant
    @PutMapping("/{id}")
    public ResponseEntity<Utilisateurs> updateUtilisateur(@PathVariable("id") Integer id, @RequestBody Utilisateurs utilisateur) {
        Optional<Utilisateurs> existingUtilisateurOpt = utilisateursService.getUtilisateurById(id);

        if (existingUtilisateurOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Utilisateurs existingUtilisateur = existingUtilisateurOpt.get();

        // Forcer le mot de passe à rester inchangé
        utilisateur.setMotDePasse(existingUtilisateur.getMotDePasse());

        // Mettre à jour les autres champs
        Utilisateurs updatedUtilisateur = utilisateursService.updateUtilisateur(id, utilisateur);

        return ResponseEntity.ok(updatedUtilisateur);
    }



    // Supprimer un utilisateur
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtilisateur(@PathVariable("id") Integer id) { // Id de type Integer
        boolean isDeleted = utilisateursService.deleteUtilisateur(id);
        return isDeleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
