

package com.eath.Service;

import com.eath.entite.Administrateur;
import java.util.List;

public interface AdministrateurService {
    Administrateur addAdministrateur(Administrateur administrateur);
    List<Administrateur> getAllAdministrateurs();
    Administrateur updateAdministrateur(Integer idAdministrateur, Administrateur administrateur);
    void deleteAdministrateur(Integer idAdministrateur);
    Administrateur getOneAdministrateur(Integer idAdministrateur);
}
