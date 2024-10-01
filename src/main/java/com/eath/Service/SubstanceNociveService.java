package com.eath.Service;

import com.eath.entite.SubstanceNocive;

import java.util.List;

public interface SubstanceNociveService {
    SubstanceNocive addSubstanceNocive(SubstanceNocive substanceNocive);
    List<SubstanceNocive> getAllSubstancesNocives();
    SubstanceNocive getSubstanceNociveById(Integer idSubstance);
    SubstanceNocive updateSubstanceNocive(Integer idSubstance, SubstanceNocive substanceNocive);
    void deleteSubstanceNocive(Integer idSubstance);
}
