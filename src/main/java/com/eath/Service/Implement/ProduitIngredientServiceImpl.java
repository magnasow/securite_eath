package com.eath.Service.Implement;

import com.eath.Service.ProduitIngredientService;
import com.eath.dao.ProduitIngredientRepository;
import com.eath.entite.ProduitIngredient;
import com.eath.entite.ProduitIngredientKey;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProduitIngredientServiceImpl implements ProduitIngredientService {

    private final ProduitIngredientRepository produitIngredientRepository;

    @Override
    public ProduitIngredient addProduitIngredient(ProduitIngredient produitIngredient) {
        return produitIngredientRepository.save(produitIngredient);
    }

    @Override
    public List<ProduitIngredient> getAllProduitIngredients() {
        return produitIngredientRepository.findAll();
    }

    @Override
    public ProduitIngredient getProduitIngredientById(ProduitIngredientKey id) {
        return produitIngredientRepository.findById(id).orElse(null);
    }

    @Override
    public ProduitIngredient updateProduitIngredient(ProduitIngredientKey id, ProduitIngredient produitIngredient) {
        ProduitIngredient existingProduitIngredient = getProduitIngredientById(id);
        if (existingProduitIngredient != null) {
            existingProduitIngredient.setQuantite(produitIngredient.getQuantite());
            return produitIngredientRepository.save(existingProduitIngredient);
        }
        return null;
    }

    @Override
    public void deleteProduitIngredient(ProduitIngredientKey id) {
        produitIngredientRepository.deleteById(id);
    }
}
