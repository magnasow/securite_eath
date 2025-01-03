package com.eath.Service;

import com.eath.entite.Allergene;
import java.util.List;

public interface AllergeneService {

    List<Allergene> getAllergenesByNames(List<String> names);
}
