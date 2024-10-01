package com.eath.web;

import com.eath.Service.PasswordResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/password-reset")
public class PasswordResetController {

    @Autowired
    private PasswordResetService passwordResetService;

    @PostMapping("/initiate")
    public ResponseEntity<String> initiatePasswordReset(@Valid @RequestBody PasswordResetRequest request) {
        boolean success = passwordResetService.initiatePasswordReset(request.getEmail());
        if (success) {
            return ResponseEntity.ok("Un lien de réinitialisation de mot de passe a été envoyé par e-mail.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erreur : L'utilisateur n'existe pas.");
        }
    }

    @PostMapping("/reset")
    public ResponseEntity<String> resetPassword(@Valid @RequestBody PasswordResetRequest request) {
        boolean success = passwordResetService.resetPassword(request.getToken(), request.getNewPassword());
        if (success) {
            return ResponseEntity.ok("Le mot de passe a été réinitialisé avec succès.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erreur : Jeton invalide ou expiré.");
        }
    }
}
