package com.eath.entite;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class Actuality {
    private String image;
    private Map<String, String> titres; // Titre dans plusieurs langues
    private Map<String, String> descriptions; // Description dans plusieurs langues
    private String auteur;
    private String date;
}
