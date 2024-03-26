package com.example.testeditions.Services;

import com.example.testeditions.Entites.Commentaire;
import com.example.testeditions.Entites.User;
import com.example.testeditions.Entites.Voiture;

import java.util.List;

public interface CommentaireService {
    List<Commentaire> getAllCommentaires();
    Commentaire getCommentaireById(Long idco);
    Commentaire saveCommentaire(Commentaire commentaire);
    Commentaire updateCommentaire(Long idco, Commentaire updatedCommentaire);
    void deleteCommentaire(Long idco);

    List<Commentaire> getCommentsByUser(User user);

    List<Commentaire> getCommentsByAnnonceCovId(Long annonceCovId);



}
