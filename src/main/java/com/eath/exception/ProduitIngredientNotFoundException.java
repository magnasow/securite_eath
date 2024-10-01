package com.eath.exception;

public class ProduitIngredientNotFoundException extends RuntimeException {
    public ProduitIngredientNotFoundException(String message) {
        super(message);
    }
}
