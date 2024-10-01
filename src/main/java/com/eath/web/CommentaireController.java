package com.eath.web;

import com.eath.Service.CommentaireService;
import com.eath.entite.Commentaire;
import com.eath.exception.CommentaireNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/api/commentaires")
@RequiredArgsConstructor
@Validated
public class CommentaireController {

    private final CommentaireService commentaireService;

    @PostMapping
    public ResponseEntity<Commentaire> createCommentaire(@Valid @RequestBody Commentaire commentaire) {
        Commentaire savedCommentaire = commentaireService.addCommentaire(commentaire);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCommentaire);
    }

    @GetMapping
    public ResponseEntity<List<Commentaire>> getAllCommentaires() {
        List<Commentaire> commentaires = commentaireService.getAllCommentaires();
        return ResponseEntity.ok(commentaires);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Commentaire> getCommentaireById(@PathVariable Integer id) {
        Commentaire commentaire = commentaireService.getCommentaireById(id);
        return ResponseEntity.ok(commentaire);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Commentaire> updateCommentaire(
            @PathVariable Integer id,
            @Valid @RequestBody Commentaire commentaire
    ) {
        Commentaire updatedCommentaire = commentaireService.updateCommentaire(id, commentaire);
        return ResponseEntity.ok(updatedCommentaire);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommentaire(@PathVariable Integer id) {
        commentaireService.deleteCommentaire(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(CommentaireNotFoundException.class)
    public ResponseEntity<String> handleCommentaireNotFoundException(CommentaireNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
