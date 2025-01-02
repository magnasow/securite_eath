package com.eath.dao;


import com.eath.entite.NutritionInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NutritionInfoRepository extends JpaRepository<NutritionInfo, Long> {
}

