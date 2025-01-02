package com.eath.Service.Implement;



import com.eath.entite.NutritionInfo;
import com.eath.dao.NutritionInfoRepository;
    import com.eath.Service.NutritionInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NutritionInfoServiceImpl implements NutritionInfoService {

    @Autowired
    private NutritionInfoRepository nutritionInfoRepository;

    @Override
    public NutritionInfo saveNutritionInfo(NutritionInfo nutritionInfo) {
        return nutritionInfoRepository.save(nutritionInfo);
    }

    @Override
    public NutritionInfo getNutritionInfoById(Long id) {
        return nutritionInfoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nutrition Info not found with id: " + id));
    }

    @Override
    public List<NutritionInfo> getAllNutritionInfo() {
        return nutritionInfoRepository.findAll();
    }

    @Override
    public void deleteNutritionInfo(Long id) {
        if (!nutritionInfoRepository.existsById(id)) {
            throw new RuntimeException("Nutrition Info not found with id: " + id);
        }
        nutritionInfoRepository.deleteById(id);
    }
}
