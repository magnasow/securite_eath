package com.eath.Service;

import com.eath.entite.Commentaire;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface CommentaireService {
    Commentaire addCommentaire(Commentaire commentaire);
    Page<Commentaire> getAllCommentaires(PageRequest pageRequest);  // Pagination ajoutée
    Commentaire getCommentaireById(Integer idCommentaire);
    Commentaire updateCommentaire(Integer idCommentaire, Commentaire commentaire);
    void deleteCommentaire(Integer idCommentaire);
}
