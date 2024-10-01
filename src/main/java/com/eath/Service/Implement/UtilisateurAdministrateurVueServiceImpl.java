package com.eath.Service.Implement;

import com.eath.entite.UtilisateurAdministrateurVue;
import com.eath.dao.UtilisateurAdministrateurVueRepository;
import com.eath.Service.UtilisateurAdministrateurVueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurAdministrateurVueServiceImpl implements UtilisateurAdministrateurVueService {

    @Autowired
    private UtilisateurAdministrateurVueRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UtilisateurAdministrateurVue user = repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé avec l'email : " + email));
        return user; // Assurez-vous que `user` implémente `UserDetails`
    }
}
