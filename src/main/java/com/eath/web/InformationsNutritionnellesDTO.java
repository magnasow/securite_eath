package com.eath.web;

import com.eath.entite.InformationsNutritionnelles;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InformationsNutritionnellesDTO {
    private Integer idInformation;
    private String nomProduit;
    private BigDecimal calories;
    private BigDecimal proteines;
    private BigDecimal graisses;
    private BigDecimal glucides;
    private String dateCreation;
    private String dateModification;

    // Method to convert from entity to DTO
    public static InformationsNutritionnellesDTO fromEntity(InformationsNutritionnelles info) {
        InformationsNutritionnellesDTO dto = new InformationsNutritionnellesDTO();
        dto.setIdInformation(info.getIdInformation());
        dto.setNomProduit(info.getProduit().getNomProduit()); // Assuming 'nomProduit' is the name of the product
        dto.setCalories(info.getCalories());
        dto.setProteines(info.getProteines());
        dto.setGraisses(info.getGraisses());
        dto.setGlucides(info.getGlucides());
        dto.setDateCreation(info.getDateCreation().toString());
        dto.setDateModification(info.getDateModification().toString());
        return dto;
    }
}
