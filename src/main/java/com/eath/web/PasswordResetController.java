package com.eath.web;

import com.eath.Service.PasswordResetService;
import com.eath.Service.UtilisateursService;
import com.eath.entite.PasswordResetToken;
import com.eath.entite.Utilisateurs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/password-reset")
public class PasswordResetController {

    @Autowired
    private PasswordResetService passwordResetService;

    @Autowired
    private UtilisateursService utilisateursService; // Service utilisateur

    @Autowired
    private PasswordEncoder passwordEncoder; // Pour encoder le mot de passe

    // API pour initier la réinitialisation de mot de passe
    @PostMapping("/initiate")
    public ResponseEntity<String> initiatePasswordReset(@Valid @RequestBody PasswordResetRequest request) {
        boolean success = passwordResetService.initiatePasswordReset(request.getEmail());
        if (success) {
            return ResponseEntity.ok("Un lien de réinitialisation de mot de passe a été envoyé par e-mail.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erreur : L'utilisateur n'existe pas.");
        }
    }

    // API pour vérifier la validité du jeton de réinitialisation
    @PostMapping("/verify-token")
    public ResponseEntity<String> verifyToken(@RequestParam String token) {
        boolean valid = passwordResetService.verifyToken(token);
        if (valid) {
            return ResponseEntity.ok("Le token est valide.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erreur : Jeton invalide ou expiré.");
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(
            @RequestParam String token,
            @Valid @RequestBody PasswordResetWithConfirmationRequest request) {

        // Vérifier et réinitialiser le mot de passe avec validation des mots de passe
        boolean success = passwordResetService.resetPassword(token, request.getNewPassword(), request.getConfirmPassword());
        if (success) {
            return ResponseEntity.ok("Le mot de passe a été réinitialisé avec succès.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erreur : Les mots de passe ne correspondent pas ou jeton invalide.");
        }
    }

    // API pour changer le mot de passe
    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@Valid @RequestBody PasswordChangeRequest request) {

        // Chercher l'utilisateur par ID
        Optional<Utilisateurs> utilisateurOpt = utilisateursService.getUtilisateurById(request.getIdPersonne());
        if (!utilisateurOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erreur : Utilisateur non trouvé.");
        }

        Utilisateurs utilisateur = utilisateurOpt.get();

        // Vérifier si l'ancien mot de passe est correct
        if (!passwordEncoder.matches(request.getOldPassword(), utilisateur.getMotDePasse())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erreur : L'ancien mot de passe est incorrect.");
        }

        // Mettre à jour le mot de passe
        utilisateur.setMotDePasse(passwordEncoder.encode(request.getNewPassword()));
        utilisateursService.updateUtilisateur(utilisateur.getIdPersonne(), utilisateur);

        return ResponseEntity.ok("Le mot de passe a été changé avec succès.");
    }

}
