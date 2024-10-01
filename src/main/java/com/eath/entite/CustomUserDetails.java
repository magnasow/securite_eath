package com.eath.entite;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class CustomUserDetails implements UserDetails {

    private final Utilisateurs utilisateur;
    private final Administrateur administrateur;

    public CustomUserDetails(Utilisateurs utilisateur) {
        this.utilisateur = utilisateur;
        this.administrateur = null;
    }

    public CustomUserDetails(Administrateur administrateur) {
        this.utilisateur = null;
        this.administrateur = administrateur;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        if (utilisateur != null) {
            for (Role role : utilisateur.getRoles()) {
                authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
            }
        } else if (administrateur != null) {
            for (Role role : administrateur.getRoles()) {
                authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
            }
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return utilisateur != null ? utilisateur.getMotDePasse() : administrateur.getMotDePasse();
    }

    @Override
    public String getUsername() {
        return utilisateur != null ? utilisateur.getEmail() : administrateur.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
