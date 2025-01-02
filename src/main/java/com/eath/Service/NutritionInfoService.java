package com.eath.Service;



import com.eath.entite.NutritionInfo;

import java.util.List;

public interface NutritionInfoService {
    NutritionInfo saveNutritionInfo(NutritionInfo nutritionInfo);

    NutritionInfo getNutritionInfoById(Long id);

    List<NutritionInfo> getAllNutritionInfo();

    void deleteNutritionInfo(Long id);
}
