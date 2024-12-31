package com.eath.Service;

import com.eath.entite.InformationsNutritionnelles;

import java.util.List;

public interface InformationsNutritionnellesService {
    InformationsNutritionnelles addInformationsNutritionnellesWithCodeBarre(String codeBarre, InformationsNutritionnelles info);
    List<InformationsNutritionnelles> getAllInformationsNutritionnelles();
    InformationsNutritionnelles getInformationsNutritionnellesById(Integer id);
    InformationsNutritionnelles updateInformationsNutritionnelles(Integer id, InformationsNutritionnelles info);
    void deleteInformationsNutritionnelles(Integer id);


}
