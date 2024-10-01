package com.eath.Service.Implement;

import com.eath.Service.ResultatAnalyseService;
import com.eath.dao.ResultatAnalyseRepository;
import com.eath.entite.ResultatAnalyse;
import com.eath.exception.ResultatAnalyseNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResultatAnalyseServiceImpl implements ResultatAnalyseService {

    private final ResultatAnalyseRepository resultatAnalyseRepository;

    @Override
    public ResultatAnalyse addResultatAnalyse(ResultatAnalyse resultatAnalyse) {
        return resultatAnalyseRepository.save(resultatAnalyse);
    }

    @Override
    public List<ResultatAnalyse> getAllResultatsAnalyse() {
        return resultatAnalyseRepository.findAll();
    }

    @Override
    public ResultatAnalyse getResultatAnalyseById(Integer idResultat) {
        return resultatAnalyseRepository.findById(idResultat)
                .orElseThrow(() -> new ResultatAnalyseNotFoundException("ResultatAnalyse not found with id: " + idResultat));
    }

    @Override
    public ResultatAnalyse updateResultatAnalyse(Integer idResultat, ResultatAnalyse resultatAnalyse) {
        ResultatAnalyse existingResultatAnalyse = getResultatAnalyseById(idResultat);
        existingResultatAnalyse.setResultatAllergene(resultatAnalyse.getResultatAllergene());
        existingResultatAnalyse.setConformiteHalal(resultatAnalyse.getConformiteHalal());
        existingResultatAnalyse.setSubstanceNocive(resultatAnalyse.getSubstanceNocive());
        return resultatAnalyseRepository.save(existingResultatAnalyse);
    }

    @Override
    public void deleteResultatAnalyse(Integer idResultat) {
        if (!resultatAnalyseRepository.existsById(idResultat)) {
            throw new ResultatAnalyseNotFoundException("ResultatAnalyse not found with id: " + idResultat);
        }
        resultatAnalyseRepository.deleteById(idResultat);
    }
}
