package com.eath.Service.Implement;

import com.eath.entite.Allergene;
    import com.eath.dao.AllergeneRepository;
import com.eath.Service.AllergeneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AllergeneServiceImpl implements AllergeneService {

    @Autowired
    private AllergeneRepository allergeneRepository;

    @Override
    public List<Allergene> getAllergenesByNames(List<String> names) {
        return allergeneRepository.findByNomAllergenesIn(names);
    }
}
