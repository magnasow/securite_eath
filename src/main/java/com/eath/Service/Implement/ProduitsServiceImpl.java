package com.eath.Service.Implement;

import com.eath.Service.IProduitsService;
import com.eath.dao.ProduitsRepository;
import com.eath.entite.Produits;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProduitsServiceImpl implements IProduitsService {
    private final ProduitsRepository produitsRepository;

    @Override
    public Produits addProduits(Produits produits) {
        return produitsRepository.save(produits);
    }

    @Override
    public List<Produits> getAllProduits() {
        return produitsRepository.findAll();
    }

    @Override
    public Produits updateProduits(Integer idProduit, Produits produits) {
        Produits existingProduits = getOneProduits(idProduit);
        existingProduits.setNomProduit(produits.getNomProduit());
        existingProduits.setTypeProduit(produits.getTypeProduit());
        existingProduits.setCodeBarre(produits.getCodeBarre());
        existingProduits.setDescriptionProduit(produits.getDescriptionProduit());
        existingProduits.setDateModification(LocalDateTime.now());
        return produitsRepository.save(existingProduits);
    }

    @Override
    public Produits getOneProduits(Integer idProduit) {
        return produitsRepository.findById(idProduit).orElseThrow(() ->
                new RuntimeException("Le produit recherché n'existe pas"));
    }


}
