package com.eath.entite;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
public class ProduitIngredientKey implements Serializable {

    private Integer idProduit;
    private Integer idIngredient;

    // Constructeur par défaut
    public ProduitIngredientKey() {
    }

    // Constructeur avec paramètres
    public ProduitIngredientKey(Integer idProduit, Integer idIngredient) {
        this.idProduit = idProduit;
        this.idIngredient = idIngredient;
    }

    // equals() and hashCode() methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProduitIngredientKey that = (ProduitIngredientKey) o;
        return Objects.equals(idProduit, that.idProduit) && Objects.equals(idIngredient, that.idIngredient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProduit, idIngredient);
    }
}
