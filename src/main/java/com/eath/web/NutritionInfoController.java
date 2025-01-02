package com.eath.web;



import com.eath.entite.NutritionInfo;
import com.eath.Service.NutritionInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/diet-info")
public class NutritionInfoController {

    @Autowired
    private NutritionInfoService nutritionInfoService;

    @PostMapping
    public NutritionInfo createNutritionInfo(@RequestBody NutritionInfo nutritionInfo) {
        return nutritionInfoService.saveNutritionInfo(nutritionInfo);
    }

    @GetMapping("/{id}")
    public NutritionInfo getNutritionInfo(@PathVariable Long id) {
        return nutritionInfoService.getNutritionInfoById(id);
    }

    @GetMapping
    public List<NutritionInfo> getAllNutritionInfo() {
        return nutritionInfoService.getAllNutritionInfo();
    }

    @DeleteMapping("/{id}")
    public void deleteNutritionInfo(@PathVariable Long id) {
        nutritionInfoService.deleteNutritionInfo(id);
    }
}

