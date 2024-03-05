package com.example.testeditions.Services;

import com.example.testeditions.Entites.Commentaire;

import java.util.List;

public interface CommentaireService {
    List<Commentaire> getAllCommentaires();
    Commentaire getCommentaireById(Long idco);
    Commentaire saveCommentaire(Commentaire commentaire);
    Commentaire updateCommentaire(Long idco, Commentaire updatedCommentaire);
    void deleteCommentaire(Long idco);
}
