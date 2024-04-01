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
            return true;
        } else {
            return false;
        }
    }



    @Override
    public Commentaire likeComment(Long commentId, Long userId) {
        // Find the comment by its ID
        Commentaire commentaire = commentaireRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found with id: " + commentId));

        // Find the user by their ID
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        // Check if the user has already liked the comment
        CommentLike existingLike = commentLikeRepository.findByCommentaireIdcoAndUserId(commentId, userId);
        if (existingLike != null) {
            deleteLikeForComment(commentId, userId);
            return null;
        } else {
            // Check if the user has already disliked the comment
            CommentDislike existingDislike = commentDislikeRepository.findByCommentaireIdcoAndUserId(commentId, userId);
            if (existingDislike != null) {
                deleteDislikeForComment(commentId, userId);
            }
            // Add a new like
            commentaire.getLikes().add(new CommentLike(commentaire, user));
            return commentaireRepository.save(commentaire);
        }
    }



    @Override
    public Commentaire dislikeComment(Long commentId, Long userId) {
        // Find the comment by its ID
        Commentaire commentaire = commentaireRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found with id: " + commentId));

        // Find the user by their ID
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        // Check if the user has already disliked the comment
        CommentDislike existingDislike = commentDislikeRepository.findByCommentaireIdcoAndUserId(commentId, userId);
        if (existingDislike != null) {
            // If the user has already disliked the comment, delete the dislike
            deleteDislikeForComment(commentId, userId);
            return null; // Return null to indicate that the dislike was removed
        } else {
            // Check if the user has already liked the comment
            CommentLike existingLike = commentLikeRepository.findByCommentaireIdcoAndUserId(commentId, userId);
            if (existingLike != null) {
                // If the user has already liked the comment, delete the like
                deleteLikeForComment(commentId, userId);
            }
            // Add a new dislike
            commentaire.getDislikes().add(new CommentDislike(commentaire, user));
            return commentaireRepository.save(commentaire); // Save and return the updated comment
        }
    }


    @Override
    public long getLikeCount(Long idco) {
        Commentaire commentaire = commentaireRepository.findById(idco)
                .orElseThrow(() -> new RuntimeException("Comment not found with id: " + idco));
        return commentaire.getLikes().size();
    }

    @Override
    public long getDislikeCount(Long idco) {
        Commentaire commentaire = commentaireRepository.findById(idco)
                .orElseThrow(() -> new RuntimeException("Comment not found with id: " + idco));
        return commentaire.getDislikes().size();
    }

    @Override
    public void deleteLikeForComment(Long idco, Long userId) {
        // Find the like by idco and userId
        CommentLike likeToDelete = commentLikeRepository.findByCommentaireIdcoAndUserId(idco, userId);
        if (likeToDelete != null) {
            // Remove the like
            commentLikeRepository.delete(likeToDelete);
        }
    }
    @Override
    public void deleteDislikeForComment(Long idco, Long userId) {
        // Find the dislike by idco and userId
        CommentDislike dislikeToDelete = commentDislikeRepository.findByCommentaireIdcoAndUserId(idco, userId);
        if (dislikeToDelete != null) {
            // Remove the dislike
            commentDislikeRepository.delete(dislikeToDelete);
        }
    }

    @Override
        public Commentaire toggleLikeForComment(Long idco, Long userId) {
        Commentaire commentaire = commentaireRepository.findById(idco)
                .orElseThrow(() -> new RuntimeException("Comment not found with id: " + idco));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        boolean alreadyLiked = commentaire.getLikes().stream()
                .anyMatch(like -> like.getUser().getId().equals(userId));

        if (alreadyLiked) {
            commentaire.getLikes().removeIf(like -> like.getUser().equals(user));
        } else {
            boolean alreadyDisliked = commentaire.getDislikes().stream()
                    .anyMatch(dislike -> dislike.getUser().getId().equals(userId));

            if (alreadyDisliked) {
                commentaire.getDislikes().removeIf(dislike -> dislike.getUser().equals(user));
            }

            commentaire.getLikes().add(new CommentLike(commentaire, user));
        }

        return commentaireRepository.save(commentaire);
    }

    @Override
    public Commentaire toggleDislikeForComment(Long idco, Long userId) {
        Commentaire commentaire = commentaireRepository.findById(idco)
                .orElseThrow(() -> new RuntimeException("Comment not found with id: " + idco));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        boolean alreadyDisliked = commentaire.getDislikes().stream()
                .anyMatch(dislike -> dislike.getUser().getId().equals(userId));

        if (alreadyDisliked) {
            commentaire.getDislikes().removeIf(dislike -> dislike.getUser().equals(user));
        } else {
            boolean alreadyLiked = commentaire.getLikes().stream()
                    .anyMatch(like -> like.getUser().getId().equals(userId));

            if (alreadyLiked) {
                commentaire.getLikes().removeIf(like -> like.getUser().equals(user));
            }

            commentaire.getDislikes().add(new CommentDislike(commentaire, user));
        }

        return commentaireRepository.save(commentaire);
    }



}






