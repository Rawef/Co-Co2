package com.example.testeditions.Services;

import com.example.testeditions.Entites.*;
import com.example.testeditions.Repositories.CommentDislikeRepository;
import com.example.testeditions.Repositories.CommentLikeRepository;
import com.example.testeditions.Repositories.CommentaireRepository;
import com.example.testeditions.Repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentaireImpl implements CommentaireService {

    @Autowired
    private CommentaireRepository commentaireRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CommentLikeRepository commentLikeRepository;

    @Autowired
    private CommentDislikeRepository commentDislikeRepository;

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
            throw new RuntimeException("Commentaire with id " + idco + " not found.");
        }
        commentaireRepository.deleteById(idco);
    }

    @Override
    public List<Commentaire> getCommentsByAnnonceCovId(Long annonceCovId) {
        return commentaireRepository.findByAnnonceCov_Ida(annonceCovId);
    }

    @Override
    public boolean deleteCommentaireByUserIdAndIdco(Long userId, Long idco) {
        Commentaire commentaire = commentaireRepository.findByUserIdAndIdco(userId, idco);
        if (commentaire != null) {
            commentaireRepository.delete(commentaire);
            return true; // Comment found and deleted successfully
        } else {
            return false; // Comment not found or deletion failed
        }
    }



    @Override
    public Commentaire likeComment(Long commentId, Long userId) {
        // Get the comment from the repository
        Commentaire commentaire = commentaireRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found with id: " + commentId));

        // Get the user from the repository
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        // Check if the user has already liked the comment
        boolean alreadyLiked = commentaire.getLikes().stream()
                .anyMatch(like -> like.getUser().getId().equals(userId));

        // Check if the user has already disliked the comment
        boolean alreadyDisliked = commentaire.getDislikes().stream()
                .anyMatch(dislike -> dislike.getUser().getId().equals(userId));

        if (alreadyLiked) {
            // User has already liked the comment, so remove the like
            commentaire.getLikes().removeIf(like -> like.getUser().getId().equals(userId));
        } else {
            // User has not liked the comment
            if (!alreadyDisliked) {
                // If the user has not disliked the comment, add the like
                commentaire.getLikes().add(new CommentLike(commentaire, user));
            } else {
                // If the user has disliked the comment, remove the dislike and add the like
                commentaire.getDislikes().removeIf(dislike -> dislike.getUser().getId().equals(userId));
                commentaire.getLikes().add(new CommentLike(commentaire, user));
            }
        }

        // Save the updated comment
        return commentaireRepository.save(commentaire);
    }

    @Override
    public Commentaire dislikeComment(Long commentId, Long userId) {
        // Get the comment from the repository
        Commentaire commentaire = commentaireRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found with id: " + commentId));

        // Get the user from the repository
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        // Check if the user has already disliked the comment
        boolean alreadyDisliked = commentaire.getDislikes().stream()
                .anyMatch(dislike -> dislike.getUser().getId().equals(userId));

        // Check if the user has already liked the comment
        boolean alreadyLiked = commentaire.getLikes().stream()
                .anyMatch(like -> like.getUser().getId().equals(userId));

        if (alreadyDisliked) {
            // User has already disliked the comment, so remove the dislike
            commentaire.getDislikes().removeIf(dislike -> dislike.getUser().getId().equals(userId));
        } else {
            // User has not disliked the comment
            if (!alreadyLiked) {
                // If the user has not liked the comment, add the dislike
                commentaire.getDislikes().add(new CommentDislike(commentaire, user));
            } else {
                // If the user has liked the comment, remove the like and add the dislike
                commentaire.getLikes().removeIf(like -> like.getUser().getId().equals(userId));
                commentaire.getDislikes().add(new CommentDislike(commentaire, user));
            }
        }

        // Save the updated comment
        return commentaireRepository.save(commentaire);
    }


}






