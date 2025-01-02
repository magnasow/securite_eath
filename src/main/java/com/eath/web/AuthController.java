package com.eath.web;

import com.eath.Service.UtilisateurAdministrateurVueService;
import com.eath.Service.UtilisateursService;
import com.eath.Service.AdministrateurService;
import com.eath.dao.RoleRepository;
import com.eath.entite.Administrateur;
import com.eath.entite.Utilisateurs;
import com.eath.entite.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
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
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getMotDePasse())
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Récupérer les rôles de l'utilisateur authentifié
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

            // Vérifier si c'est un administrateur
            if (authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
                return ResponseEntity.ok("Login successful. Welcome, Admin!");
            }

            // Sinon, c'est un utilisateur
            Utilisateurs user = utilisateursService.findByEmail(loginRequest.getEmail())
                    .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));

            LoginResponse loginResponse = new LoginResponse(
                    "Login successful. Welcome, " + user.getNomPersonne() + "!",
                    user.getIdPersonne(),
                    user.getNomPersonne(),
                    user.getPrenomPersonne(),
                    user.getEmail(),
                    String.join(", ", user.getPreferences()),
                    user.getNiveauAbonnement(),
                    user.getPhotoDeProfil(),
                    user.getPoids(),
                    user.getTaille(),
                    user.getAge(),
                    user.getSexe(),
                    user.getConditionsMedicales(),
                    authorities.stream()
                            .map(GrantedAuthority::getAuthority)
                            .collect(Collectors.joining(", "))
            );

            return ResponseEntity.ok(loginResponse);

        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Login failed: " + e.getMessage());
        }
    }
    @PutMapping("/users/{userId}/preferences")
    public ResponseEntity<String> updateUserPreferences(@PathVariable Integer userId, @RequestBody List<String> preferences) {
        Optional<Utilisateurs> utilisateurOpt = utilisateursService.getUtilisateurById(userId);

        if (utilisateurOpt.isPresent()) {
            Utilisateurs utilisateur = utilisateurOpt.get();
            utilisateur.setPreferences(preferences);
            utilisateursService.updateUtilisateur(userId, utilisateur);
            return ResponseEntity.ok("Préférences mises à jour avec succès");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Utilisateur non trouvé");
        }
    }
    // Mettre à jour les conditions médicales d'un utilisateur
    @PutMapping("/{id}/conditions-medicales/update")
    public ResponseEntity<String> updateConditionsMedicalesWithNewEndpoint(
            @PathVariable("id") Integer id,
            @RequestBody String conditionsMedicales) {

        Optional<Utilisateurs> utilisateurOpt = utilisateursService.getUtilisateurById(id);
        if (utilisateurOpt.isEmpty()) {
            return ResponseEntity.notFound().build();  // Utilisateur non trouvé
        }

        Utilisateurs utilisateur = utilisateurOpt.get();
        utilisateur.setConditionsMedicales(conditionsMedicales);  // Mise à jour des conditions médicales
        utilisateursService.updateUtilisateur(id, utilisateur);  // Mise à jour dans la base de données

        return ResponseEntity.ok("Les conditions médicales ont été mises à jour avec succès");
    }

    // Récupérer les conditions médicales d'un utilisateur
    @GetMapping("/{id}/conditions-medicales")
    public ResponseEntity<String> getConditionsMedicales(@PathVariable("id") Integer id) {
        Optional<Utilisateurs> utilisateurOpt = utilisateursService.getUtilisateurById(id);
        if (utilisateurOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(utilisateurOpt.get().getConditionsMedicales());
    }
}
