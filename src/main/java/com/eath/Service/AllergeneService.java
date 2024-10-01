package com.eath.Service;

import com.eath.entite.Allergene;

import java.util.List;
import java.util.Optional;

public interface AllergeneService {

    List<Allergene> getAllAllergenes();

    Optional<Allergene> getAllergeneById(int id);

    Allergene saveAllergene(Allergene allergene);

    void deleteAllergene(int id);

    Allergene updateAllergene(int id, Allergene allergeneDetails);
}
