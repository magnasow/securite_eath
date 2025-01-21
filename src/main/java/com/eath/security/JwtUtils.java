package com.eath.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtUtils {

    private static final String SECRET_KEY = "mysecretkey";
    private static final long EXPIRATION_TIME = 86400000L; // 24 heures

    // Générer le JWT à partir de l'email et des rôles
    public String generateJwtToken(String email, Collection<? extends GrantedAuthority> authorities) {
        Algorithm algorithm = Algorithm.HMAC512(SECRET_KEY);

        return JWT.create()
                .withSubject(email)
                .withClaim("roles", authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(algorithm);
    }

    // Extraire l'email du token JWT
    public String extractEmail(String token) {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(SECRET_KEY))
                .build()
                .verify(token);
        return decodedJWT.getSubject();
    }

    // Vérifier la validité du token
    public boolean validateJwtToken(String authToken) {
        try {
            JWT.require(Algorithm.HMAC512(SECRET_KEY)).build().verify(authToken);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Extraire les autorités (rôles) du JWT
    public Collection<GrantedAuthority> getAuthoritiesFromJwt(String token) {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(SECRET_KEY))
                .build()
                .verify(token);

        // Récupérer les rôles du token
        var roles = decodedJWT.getClaim("roles").asList(String.class);

        // Convertir les rôles en une collection d'GrantedAuthority
        return roles.stream()
                .map(role -> (GrantedAuthority) () -> "ROLE_" + role)  // Préfixe "ROLE_" requis par Spring Security
                .collect(Collectors.toList());
    }

    // Extraire la date d'expiration du token JWT
    public Date extractExpiration(String token) {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(SECRET_KEY))
                .build()
                .verify(token);
        return decodedJWT.getExpiresAt(); // Retourne la date d'expiration
    }
}
