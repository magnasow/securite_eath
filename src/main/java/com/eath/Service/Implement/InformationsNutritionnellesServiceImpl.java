package com.eath.Service.Implement;

import com.eath.Service.InformationsNutritionnellesService;
import com.eath.dao.InformationsNutritionnellesRepository;
import com.eath.dao.ProduitsRepository;
import com.eath.entite.InformationsNutritionnelles;
import com.eath.entite.Produits;
import com.eath.exception.InformationsNutritionnellesNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InformationsNutritionnellesServiceImpl implements InformationsNutritionnellesService {

    private final InformationsNutritionnellesRepository infoRepo;
    private final ProduitsRepository produitsRepo;

    @Override
    public InformationsNutritionnelles addInformationsNutritionnellesWithCodeBarre(String codeBarre, InformationsNutritionnelles info) {
        // Vérifier si un produit avec le codeBarre existe déjà
        Optional<Produits> existingProduit = produitsRepo.findByCodeBarre(codeBarre);

        if (existingProduit.isPresent()) {
            // Associer le produit existant aux informations nutritionnelles
            info.setProduit(existingProduit.get());
        } else {
            // Si le produit n'existe pas, on crée un nouveau produit
            Produits newProduit = new Produits();
            newProduit.setCodeBarre(codeBarre);
            newProduit.setNomProduit("Produit avec code " + codeBarre);
            newProduit.setDescriptionProduit("Description du produit pour " + codeBarre);

            // Sauvegarder le nouveau produit
            newProduit = produitsRepo.save(newProduit);

            // Associer le nouveau produit aux informations nutritionnelles
            info.setProduit(newProduit);
        }

        // Sauvegarde des informations nutritionnelles
        return infoRepo.save(info);
    }

    @Override
    public List<InformationsNutritionnelles> getAllInformationsNutritionnelles() {
        return infoRepo.findAll();
    }

    @Override
    public InformationsNutritionnelles getInformationsNutritionnellesById(Integer id) {
        return infoRepo.findById(id)
                .orElseThrow(() -> new InformationsNutritionnellesNotFoundException("InformationsNutritionnelles not found with id: " + id));
    }

    @Override
    public InformationsNutritionnelles updateInformationsNutritionnelles(Integer id, InformationsNutritionnelles info) {
        InformationsNutritionnelles existingInfo = getInformationsNutritionnellesById(id);
        existingInfo.setCalories(info.getCalories());
        existingInfo.setProteines(info.getProteines());
        existingInfo.setGraisses(info.getGraisses());
        existingInfo.setGlucides(info.getGlucides());
        existingInfo.setProduit(info.getProduit());
        return infoRepo.save(existingInfo);
    }

    @Override
    public void deleteInformationsNutritionnelles(Integer id) {
        if (!infoRepo.existsById(id)) {
            throw new InformationsNutritionnellesNotFoundException("InformationsNutritionnelles not found with id: " + id);
        }
        infoRepo.deleteById(id);
    }

    @Override
    public Optional<InformationsNutritionnelles> getInformationsNutritionnellesByProduitId(Integer idProduit) {
        // Trouver les informations nutritionnelles par l'id du produit
        return infoRepo.findByProduit_IdProduit(idProduit);
    }

    private final InformationsNutritionnellesRepository informationsNutritionnellesRepository;

    // Méthode pour récupérer les informations nutritionnelles associées à un produit
    public Optional<InformationsNutritionnelles> findByProduit(Produits produit) {
        return informationsNutritionnellesRepository.findByProduit(produit);
    }
}
