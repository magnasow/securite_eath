package com.eath.Service;

import com.eath.entite.ResultatAnalyse;

import java.util.List;

public interface ResultatAnalyseService {

    ResultatAnalyse addResultatAnalyse(ResultatAnalyse resultatAnalyse);

    List<ResultatAnalyse> getAllResultatsAnalyse();

    ResultatAnalyse getResultatAnalyseById(Integer idResultat);

    ResultatAnalyse updateResultatAnalyse(Integer idResultat, ResultatAnalyse resultatAnalyse);

    void deleteResultatAnalyse(Integer idResultat);
}
