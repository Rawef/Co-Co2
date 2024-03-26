package com.example.testeditions.Services;

import com.example.testeditions.Entites.Commentaire;
import com.example.testeditions.Entites.User;
import com.example.testeditions.Entites.Voiture;
import com.example.testeditions.Repositories.CommentaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentaireImpl implements CommentaireService {

    @Autowired
    private CommentaireRepository commentaireRepository;

    @Override
    public List<Commentaire> getAllCommentaires() {
        return commentaireRepository.findAll();
    }

    @Override
    public Commentaire getCommentaireById(Long idco) {
        return commentaireRepository.findById(idco)
                .orElseThrow(() -> new RuntimeException("Commentaire not found with id: " + idco));
    }

    @Override
    public List<Commentaire> getCommentsByUser(User user) {
        return commentaireRepository.findByUser(user);
    }
    @Override
    public Commentaire saveCommentaire(Commentaire commentaire) {
        return commentaireRepository.save(commentaire);
    }

    @Override
    public Commentaire updateCommentaire(Long idco, Commentaire updatedCommentaire) {
        Optional<Commentaire> commentaireOptional = commentaireRepository.findById(idco);
        if (commentaireOptional.isPresent()) {
            updatedCommentaire.setIdco(idco);
            return commentaireRepository.save(updatedCommentaire);
        }
        return null;
    }

    @Override
    public void deleteCommentaire(Long idco) {

        if (!commentaireRepository.existsById(idco)) {
            throw new RuntimeException("Circuit with id " + idco + " not found.");
        }
        commentaireRepository.deleteById(idco);
    }

    @Override
    public List<Commentaire> getCommentsByAnnonceCovId(Long annonceCovId) {
        return commentaireRepository.findByAnnonceCov_Ida(annonceCovId);
    }

    }



