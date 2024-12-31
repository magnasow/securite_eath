package com.eath.Service;

import com.eath.entite.Produits;

import java.util.List;

public interface IProduitsService {
    Produits addProduits(Produits produits);

    List<Produits> getAllProduits();

    Produits updateProduits(Integer idProduit, Produits produits);

    Produits getOneProduits(Integer idProduit);

}
