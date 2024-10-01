package com.eath.Service.Implement;

import com.eath.Service.AdministrateurService;
import com.eath.dao.AdministrateurRepository;
import com.eath.entite.Administrateur;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdministrateurServiceImpl implements AdministrateurService {

    private final AdministrateurRepository administrateurRepository;

    @Override
    public Administrateur addAdministrateur(Administrateur administrateur) {
        return administrateurRepository.save(administrateur);
    }

    @Override
    public List<Administrateur> getAllAdministrateurs() {
        return administrateurRepository.findAll();
    }

    @Override
    public Administrateur updateAdministrateur(Integer idPersonne, Administrateur administrateur) {
        if (administrateurRepository.existsById(idPersonne)) {
            administrateur.setIdPersonne(idPersonne);  // Utilisez setIdPersonne
            return administrateurRepository.save(administrateur);
        } else {
            throw new RuntimeException("Administrateur not found with id " + idPersonne);
        }
    }

    @Override
    public void deleteAdministrateur(Integer idPersonne) {
        if (administrateurRepository.existsById(idPersonne)) {
            administrateurRepository.deleteById(idPersonne);
        } else {
            throw new RuntimeException("Administrateur not found with id " + idPersonne);
        }
    }

    @Override
    public Administrateur getOneAdministrateur(Integer idPersonne) {
        return administrateurRepository.findById(idPersonne)
                .orElseThrow(() -> new RuntimeException("Administrateur not found with id " + idPersonne));
    }
}