package com.eath.web;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class InformationNutritionnelleDTO {
    private Integer idInformation;
    private BigDecimal calories;
    private BigDecimal glucides;
    private BigDecimal graisses;
    private BigDecimal proteines;
    private LocalDateTime dateCreation;
    private LocalDateTime dateModification;
}
