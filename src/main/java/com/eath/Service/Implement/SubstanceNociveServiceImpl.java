package com.eath.Service.Implement;

import com.eath.Service.SubstanceNociveService;
import com.eath.dao.SubstanceNociveRepository;
import com.eath.entite.SubstanceNocive;
import com.eath.exception.SubstanceNociveNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubstanceNociveServiceImpl implements SubstanceNociveService {

    private final SubstanceNociveRepository substanceNociveRepository;

    @Override
    public SubstanceNocive addSubstanceNocive(SubstanceNocive substanceNocive) {
        return substanceNociveRepository.save(substanceNocive);
    }

    @Override
    public List<SubstanceNocive> getAllSubstancesNocives() {
        return substanceNociveRepository.findAll();
    }

    @Override
    public SubstanceNocive getSubstanceNociveById(Integer idSubstance) {
        return substanceNociveRepository.findById(idSubstance)
                .orElseThrow(() -> new SubstanceNociveNotFoundException("Substance not found with id: " + idSubstance));
    }

    @Override
    public SubstanceNocive updateSubstanceNocive(Integer idSubstance, SubstanceNocive substanceNocive) {
        SubstanceNocive existingSubstanceNocive = getSubstanceNociveById(idSubstance);
        existingSubstanceNocive.setNomSubstance(substanceNocive.getNomSubstance());
        existingSubstanceNocive.setTypeSubstance(substanceNocive.getTypeSubstance());
        return substanceNociveRepository.save(existingSubstanceNocive);
    }

    @Override
    public void deleteSubstanceNocive(Integer idSubstance) {
        if (!substanceNociveRepository.existsById(idSubstance)) {
            throw new SubstanceNociveNotFoundException("Substance not found with id: " + idSubstance);
        }
        substanceNociveRepository.deleteById(idSubstance);
    }
}
