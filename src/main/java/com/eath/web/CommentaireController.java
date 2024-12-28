package com.eath.web;

import com.eath.Service.CommentaireService;
import com.eath.entite.Commentaire;
import com.eath.entite.Utilisateurs;
import com.eath.exception.CommentaireNotFoundException;
import com.eath.dao.UtilisateursRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/commentaires")
@RequiredArgsConstructor
@Validated
public class CommentaireController {

    private final CommentaireService commentaireService;
    private final UtilisateursRepository utilisateursRepository;

    @PostMapping
    public ResponseEntity<CommentaireResponse> addCommentaire(@Valid @RequestBody CommentaireRequest commentaireRequest) {
        // Vérifier si l'ID utilisateur est valide
        if (commentaireRequest.getUtilisateurId() <= 0) {
            throw new CommentaireNotFoundException("ID utilisateur invalide");
        }

        Utilisateurs utilisateur = utilisateursRepository
                .findById(commentaireRequest.getUtilisateurId())
                .orElseThrow(() -> new CommentaireNotFoundException("Utilisateur non trouvé"));

        Commentaire commentaire = new Commentaire();
        commentaire.setUtilisateur(utilisateur);
        commentaire.setCommentaire(commentaireRequest.getCommentaire());
        commentaire.setNote(commentaireRequest.getNote());

        Commentaire savedCommentaire = commentaireService.addCommentaire(commentaire);

        // Convertir l'entité Commentaire en CommentaireResponse pour ne retourner que les champs désirés
        CommentaireResponse response = new CommentaireResponse();
        response.setIdCommentaire(savedCommentaire.getIdCommentaire());
        response.setCommentaire(savedCommentaire.getCommentaire());
        response.setNote(savedCommentaire.getNote());
        response.setDateCreation(savedCommentaire.getDateCreation());
        response.setDateModification(savedCommentaire.getDateModification());

        // Remplir les informations de l'utilisateur lié
        response.setNomPersonne(savedCommentaire.getUtilisateur().getNomPersonne());
        response.setPrenomPersonne(savedCommentaire.getUtilisateur().getPrenomPersonne());
        response.setPhotoDeProfil(savedCommentaire.getUtilisateur().getPhotoDeProfil());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<Page<CommentaireResponse>> getAllCommentaires(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "dateCreation") String sortBy,
            @RequestParam(defaultValue = "desc") String direction
    ) {
        // Créer un objet PageRequest avec les paramètres de pagination et de tri
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(direction), sortBy));

        // Obtenez une page de commentaires avec la pagination et l'ordre décroissant selon la date de création
        Page<Commentaire> commentairePage = commentaireService.getAllCommentaires(pageRequest);

        // Convertir la page de Commentaire en une page de CommentaireResponse
        Page<CommentaireResponse> responsePage = commentairePage.map(commentaire -> {
            CommentaireResponse response = new CommentaireResponse();
            response.setIdCommentaire(commentaire.getIdCommentaire());
            response.setCommentaire(commentaire.getCommentaire());
            response.setNote(commentaire.getNote());
            response.setDateCreation(commentaire.getDateCreation());
            response.setDateModification(commentaire.getDateModification());

            // Remplir les informations de l'utilisateur lié
            Utilisateurs utilisateur = commentaire.getUtilisateur();
            response.setNomPersonne(utilisateur.getNomPersonne());
            response.setPrenomPersonne(utilisateur.getPrenomPersonne());
            response.setPhotoDeProfil(utilisateur.getPhotoDeProfil());

            return response;
        });

        return ResponseEntity.ok(responsePage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentaireResponse> getCommentaireById(@PathVariable Integer id) {
        Commentaire commentaire = commentaireService.getCommentaireById(id);

        // Convertir l'entité Commentaire en CommentaireResponse pour ne retourner que les champs désirés
        CommentaireResponse response = new CommentaireResponse();
        response.setIdCommentaire(commentaire.getIdCommentaire());
        response.setCommentaire(commentaire.getCommentaire());
        response.setNote(commentaire.getNote());
        response.setDateCreation(commentaire.getDateCreation());
        response.setDateModification(commentaire.getDateModification());

        // Remplir les informations de l'utilisateur lié
        Utilisateurs utilisateur = commentaire.getUtilisateur();
        response.setNomPersonne(utilisateur.getNomPersonne());
        response.setPrenomPersonne(utilisateur.getPrenomPersonne());
        response.setPhotoDeProfil(utilisateur.getPhotoDeProfil());

        return ResponseEntity.ok(response);
    }
}
