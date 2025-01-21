package com.eath.config;

import com.eath.Service.UtilisateurAdministrateurVueService;
import com.eath.security.JwtAuthenticationFilter;
import com.eath.security.JwtUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.beans.factory.annotation.Autowired;

@Configuration
public class SecurityConfig {

    private final JwtUtils tokenProvider;
    private final UtilisateurAdministrateurVueService utilisateurService;

    @Autowired
    public SecurityConfig(JwtUtils tokenProvider, UtilisateurAdministrateurVueService utilisateurService) {
        this.tokenProvider = tokenProvider;
        this.utilisateurService = utilisateurService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Utilisation de BCrypt pour le hachage des mots de passe
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(tokenProvider);

        http
                .csrf().disable() // Désactive la protection CSRF si vous utilisez JWT
                .authorizeHttpRequests()
                .requestMatchers("/api/auth/**").permitAll() // Permet l'accès public aux routes d'authentification
                .requestMatchers("/api/v1/voice/process").permitAll()
                .requestMatchers("/text-to-speech/**").permitAll() // Ajout de la route pour le service de synthèse vocale
                .requestMatchers("/audio/**").permitAll()
                .requestMatchers("/webhook/**").permitAll()
                .requestMatchers("/api/actualites").permitAll()
                .requestMatchers("/api/nutritions").permitAll()
                .requestMatchers("/api/recettes").permitAll()
                .requestMatchers("/api/diet-info").permitAll()

                .anyRequest().authenticated() // Requiert une authentification pour les autres requêtes
                .and()
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); // Ajoute le filtre JWT avant le filtre d'authentification standard

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    // Configurer CORS pour permettre l'accès à certaines origines
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
