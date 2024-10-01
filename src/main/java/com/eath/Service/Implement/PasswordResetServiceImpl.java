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

import java.util.Optional;
import java.util.Random;

@Service
public class PasswordResetServiceImpl implements PasswordResetService {

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    private UtilisateursRepository utilisateursRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final Random RANDOM = new Random();

    @Override
    public boolean initiatePasswordReset(String email) {
        Optional<Utilisateurs> optionalUtilisateur = utilisateursRepository.findByEmail(email);

        if (optionalUtilisateur.isPresent()) {
            Utilisateurs utilisateur = optionalUtilisateur.get();
            String token = generateResetCode(); // Utilisation du code à 3 chiffres

            // Créer un nouveau token
            PasswordResetToken passwordResetToken = new PasswordResetToken(token, utilisateur);
            passwordResetTokenRepository.save(passwordResetToken);

            // Envoyer l'e-mail avec le token
            emailService.sendPasswordResetEmail(utilisateur.getEmail(), token);
            return true;
        } else {
            return false; // Utilisateur non trouvé
        }
    }

    @Override
    public boolean resetPassword(String token, String newPassword) {
        Optional<PasswordResetToken> optionalToken = passwordResetTokenRepository.findByToken(token);

        // Vérifier si le jeton existe
        if (optionalToken.isPresent()) {
            PasswordResetToken passwordResetToken = optionalToken.get();

            // Vérifier si le jeton est expiré
            if (passwordResetToken.isExpired()) {
                return false; // Le jeton est expiré
            }

            // Récupérer l'utilisateur associé
            Utilisateurs utilisateur = passwordResetToken.getUtilisateur();

            // Réinitialiser le mot de passe
            utilisateur.setMotDePasse(passwordEncoder.encode(newPassword));
            utilisateursRepository.save(utilisateur); // Sauvegarder le nouveau mot de passe

            // Supprimer le jeton après réinitialisation
            passwordResetTokenRepository.delete(passwordResetToken);

            return true; // Réinitialisation réussie
        } else {
            return false; // Jeton invalide
        }
    }

    private String generateResetCode() {
        int code = RANDOM.nextInt(900) + 100; // Génère un nombre entre 100 et 999
        return String.valueOf(code);
    }

}
