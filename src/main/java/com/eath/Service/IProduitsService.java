package com.eath.Service;

import com.eath.entite.Produits;

import java.util.List;
import java.util.Optional;

public interface IProduitsService {
    Produits addProduits(Produits produits);

    List<Produits> getAllProduits();

    Produits updateProduits(Integer idProduit, Produits produits);

    Produits getOneProduits(Integer idProduit);
    Optional<Produits> findById(Integer id);

}
