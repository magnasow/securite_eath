package com.eath.entite;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class Nutrition {
    private String image;
    private Map<String, String> titre; // Titres dans différentes langues
    private Map<String, String> description; // Descriptions dans différentes langues
    private String auteur;
    private String date;
}
