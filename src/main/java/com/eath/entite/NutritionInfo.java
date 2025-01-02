package com.eath.entite;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class NutritionInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String label;
    private int currentValue;
    private int maxValue;
    private String unit;

    private int currentCalories;
    private int maxCalories;
}
