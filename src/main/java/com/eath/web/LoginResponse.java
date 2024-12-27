package com.eath.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private String message;
    private String nomPersonne;
    private String prenomPersonne;
    private String email;
    private String preferences;
    private String niveauAbonnement;
    private String photoDeProfil;
    private BigDecimal poids;
    private Integer age;
    private String sexe;
    private String conditionsMedicales;
    private String role;
}
