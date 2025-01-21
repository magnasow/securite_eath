package com.eath.web;

import com.eath.Service.UtilisateurAdministrateurVueService;
import com.eath.security.JwtUtils;
import com.eath.Service.UtilisateursService;
import com.eath.Service.AdministrateurService;
import com.eath.dao.RoleRepository;
import com.eath.entite.Utilisateurs;
import com.eath.entite.Administrateur;
import com.eath.entite.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

import java.util.List;
import java.util.Optional;
import java.util.Date;
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UtilisateursService utilisateursService;

    @Autowired
    private AdministrateurService administrateurService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private UtilisateurAdministrateurVueService utilisateurAdministrateurVueService;
    private final JwtUtils jwtUtils;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Utilisateurs user) {
        try {
            System.out.println("Preferences received: " + user.getPreferences());

            Role userRole = roleRepository.findByName("USER")
                    .orElseThrow(() -> new RuntimeException("Role USER not found"));

            user.setRoles(Set.of(userRole));
            user.setMotDePasse(passwordEncoder.encode(user.getMotDePasse()));
            utilisateursService.creerUtilisateur(user);

            return ResponseEntity.ok("User registered successfully with USER role");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred: " + e.getMessage());
        }
    }

    @PostMapping("/registerAdmin")
    public ResponseEntity<String> registerAdmin(@RequestBody Administrateur admin) {
        Role adminRole = roleRepository.findByName("ADMIN")
                .orElseThrow(() -> new RuntimeException("Role ADMIN not found"));

        admin.setRoles(Set.of(adminRole));
        admin.setMotDePasse(passwordEncoder.encode(admin.getMotDePasse()));
        administrateurService.addAdministrateur(admin);

        return ResponseEntity.ok("Admin registered successfully with ADMIN role");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            // Authentifier l'utilisateur
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getMotDePasse())
            );

            // Récupérer le contexte de sécurité
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Générer le token JWT
            String jwt = jwtUtils.generateJwtToken(loginRequest.getEmail(), authentication.getAuthorities());

            // Extraire la date d'expiration du token
            Date expirationDate = jwtUtils.extractExpiration(jwt);

            // Créer la réponse avec le token et la date d'expiration
            JwtResponse jwtResponse = new JwtResponse(
                    jwt,
                    expirationDate
            );

            // Retourner seulement le token et la date d'expiration
            return ResponseEntity.ok(jwtResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed: " + e.getMessage());
        }
    }

    @PutMapping("/users/{userId}/preferences")
    public ResponseEntity<String> updateUserPreferences(@PathVariable Integer userId, @RequestBody List<String> preferences) {
        Optional<Utilisateurs> utilisateurOpt = utilisateursService.getUtilisateurById(userId);
        if (utilisateurOpt.isPresent()) {
            Utilisateurs utilisateur = utilisateurOpt.get();
            utilisateur.setPreferences(preferences);
            utilisateursService.updateUtilisateur(userId, utilisateur);
            return ResponseEntity.ok("Preferences updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    @PutMapping("/{id}/conditions-medicales/update")
    public ResponseEntity<String> updateConditionsMedicales(@PathVariable("id") Integer id, @RequestBody String conditionsMedicales) {
        Optional<Utilisateurs> utilisateurOpt = utilisateursService.getUtilisateurById(id);
        if (utilisateurOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Utilisateurs utilisateur = utilisateurOpt.get();
        utilisateur.setConditionsMedicales(conditionsMedicales);
        utilisateursService.updateUtilisateur(id, utilisateur);

        return ResponseEntity.ok("Conditions updated successfully");
    }
}
