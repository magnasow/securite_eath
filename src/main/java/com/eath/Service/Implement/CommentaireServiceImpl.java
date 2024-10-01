package com.eath.Service.Implement;

import com.eath.Service.CommentaireService;
import com.eath.dao.CommentaireRepository;
import com.eath.entite.Commentaire;
import com.eath.exception.CommentaireNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentaireServiceImpl implements CommentaireService {

    private final CommentaireRepository commentaireRepository;

    @Override
    public Commentaire addCommentaire(Commentaire commentaire) {
        return commentaireRepository.save(commentaire);
    }

    @Override
    public List<Commentaire> getAllCommentaires() {
        return commentaireRepository.findAll();
    }

    @Override
    public Commentaire getCommentaireById(Integer idCommentaire) {
        return commentaireRepository.findById(idCommentaire)
                .orElseThrow(() -> new CommentaireNotFoundException("Commentaire not found with id: " + idCommentaire));
    }

    @Override
    public Commentaire updateCommentaire(Integer idCommentaire, Commentaire commentaire) {
        Commentaire existingCommentaire = getCommentaireById(idCommentaire);
        existingCommentaire.setCommentaire(commentaire.getCommentaire());
        existingCommentaire.setNote(commentaire.getNote());
        return commentaireRepository.save(existingCommentaire);
    }

    @Override
    public void deleteCommentaire(Integer idCommentaire) {
        if (!commentaireRepository.existsById(idCommentaire)) {
            throw new CommentaireNotFoundException("Commentaire not found with id: " + idCommentaire);
        }
        commentaireRepository.deleteById(idCommentaire);
    }
}
