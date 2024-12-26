package com.eath.Service.Implement;

import com.eath.Service.PasswordResetService;
import com.eath.Service.EmailService;
import com.eath.dao.PasswordResetTokenRepository;
import com.eath.dao.UtilisateursRepository;
import com.eath.entite.PasswordResetToken;
import com.eath.entite.Utilisateurs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import java.security.SecureRandom;

@Service
public class PasswordResetServiceImpl implements PasswordResetService {

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    private UtilisateursRepository utilisateursRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    @Override
    public boolean initiatePasswordReset(String email) {
        Optional<Utilisateurs> utilisateurOpt = utilisateursRepository.findByEmail(email);

        if (utilisateurOpt.isPresent()) {
            Utilisateurs utilisateur = utilisateurOpt.get();

            // Générer un token de 6 chiffres pour la réinitialisation
            String token = generateSixDigitToken();

            // Définir la date d'expiration du token (par exemple, 1 heure)
            LocalDateTime expirationDate = LocalDateTime.now().plusHours(1);

            // Créer un objet PasswordResetToken
            PasswordResetToken passwordResetToken = new PasswordResetToken();
            passwordResetToken.setToken(token);
            passwordResetToken.setUtilisateur(utilisateur);
            passwordResetToken.setExpirationDate(expirationDate);

            // Enregistrer le token dans la base de données
            passwordResetTokenRepository.save(passwordResetToken);

            // Envoi du lien de réinitialisation par e-mail
            emailService.sendPasswordResetEmail(utilisateur.getEmail(), token);

            return true; // Succès
        }

        return false; // Utilisateur non trouvé
    }

    @Override
    public boolean verifyToken(String token) {
        Optional<PasswordResetToken> optionalToken = passwordResetTokenRepository.findByToken(token);

        if (optionalToken.isPresent()) {
            PasswordResetToken passwordResetToken = optionalToken.get();
            return !passwordResetToken.isExpired(); // Le token est valide tant qu'il n'est pas expiré
        }

        return false; // Token invalide
    }

    @Override
    public boolean resetPassword(String token, String newPassword, String confirmPassword) {
        if (!newPassword.equals(confirmPassword)) {
            return false; // Les mots de passe ne correspondent pas
        }

        Optional<PasswordResetToken> optionalToken = passwordResetTokenRepository.findByToken(token);

        if (optionalToken.isPresent()) {
            PasswordResetToken passwordResetToken = optionalToken.get();

            if (passwordResetToken.isExpired()) {
                return false; // Le token est expiré
            }

            // Récupérer l'utilisateur associé au token
            Utilisateurs utilisateur = passwordResetToken.getUtilisateur();

            // Mettre à jour le mot de passe
            utilisateur.setMotDePasse(passwordEncoder.encode(newPassword));

            // Sauvegarder l'utilisateur avec son nouveau mot de passe
            utilisateursRepository.save(utilisateur);

            // Supprimer le token une fois utilisé
            passwordResetTokenRepository.delete(passwordResetToken);

            return true; // Réinitialisation réussie
        }

        return false; // Token invalide
    }

    private String generateSixDigitToken() {
        SecureRandom random = new SecureRandom();
        int token = 100000 + random.nextInt(900000); // Génère un nombre entre 100000 et 999999
        return String.valueOf(token);
    }
}
