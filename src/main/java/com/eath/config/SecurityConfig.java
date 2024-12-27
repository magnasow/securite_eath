package com.eath.config;

import com.eath.Service.UtilisateurAdministrateurVueService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UtilisateurAdministrateurVueService utilisateurService;

    public SecurityConfig(UtilisateurAdministrateurVueService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Utilisation de BCrypt pour le hachage des mots de passe
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Désactive la protection CSRF si vous utilisez JWT ou une autre méthode de sécurisation
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/api/auth/**").permitAll() // Permet l'accès public aux routes d'inscription et de connexion
                                .requestMatchers("/api/prodIngredients/**").permitAll()
                                .requestMatchers("/api/historique-scans/**").permitAll()
                                .requestMatchers("/api/types-produits/**").permitAll()
                                .requestMatchers("/api/normes-halal/**").permitAll()
                                .requestMatchers("/api/produits/**").permitAll()
                                .requestMatchers("/api/ingredients/**").permitAll()
                                .requestMatchers("/api/produits-ingredients/**").permitAll()
                                .requestMatchers("/api/allergenes/**").permitAll()
                                .requestMatchers("/api/substances-nocives/**").permitAll()
                                .requestMatchers("/api/informations-nutritionnelles/**").permitAll()
                                .requestMatchers("/api/commentaires/**").permitAll()
                                .requestMatchers("/api/resultats-analyse/**").permitAll()
                                .requestMatchers("/api/v1/voice/process").permitAll()
                                .requestMatchers("/text-to-speech/**").permitAll() // Ajout de la route pour le service de synthèse vocale
                                .requestMatchers("/audio/**").permitAll()
                                .requestMatchers("/webhook/**").permitAll()
                                .requestMatchers("/api/utilisateurs/**").permitAll()
                                .requestMatchers("/api/personnes/**").permitAll()
                                .requestMatchers("/api/password-reset/**").permitAll()
                                .requestMatchers("/api/preferences/**").permitAll()
                                .requestMatchers("/api/actualites").permitAll()
                                .anyRequest().authenticated() // Requiert une authentification pour toutes les autres requêtes
                )
                .exceptionHandling()
                .authenticationEntryPoint((request, response, authException) -> {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
                }); // Configuration de l'EntryPoint personnalisé

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
    // Bean pour configurer CORS
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:53054") // Remplacez par l'origine de votre frontend
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*");
            }
        };
    }
}
