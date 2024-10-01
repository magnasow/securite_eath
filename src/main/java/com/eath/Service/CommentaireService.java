package com.eath.Service;

import com.eath.entite.Commentaire;
import java.util.List;

public interface CommentaireService {
    Commentaire addCommentaire(Commentaire commentaire);
    List<Commentaire> getAllCommentaires();
    Commentaire getCommentaireById(Integer idCommentaire);
    Commentaire updateCommentaire(Integer idCommentaire, Commentaire commentaire);
    void deleteCommentaire(Integer idCommentaire);
}
