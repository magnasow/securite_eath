package com.eath.web;

import lombok.Data;

@Data  // Lombok génère les getters, setters, toString, equals, et hashCode
public class HistoriqueScanDTO {
    private Integer idHistoriqueScan;
    private String nomProduit;
    private InformationNutritionnelleDTO informationNutritionnelle;
    private String normeHalal;
}
