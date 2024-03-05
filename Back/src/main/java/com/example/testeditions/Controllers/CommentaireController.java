package com.example.testeditions.Controllers;

import com.example.testeditions.Entites.AnnonceCov;
import com.example.testeditions.Entites.Commentaire;
import com.example.testeditions.Entites.User;
import com.example.testeditions.Repositories.AnnonceCovRepository;
import com.example.testeditions.Repositories.UserRepository;
import com.example.testeditions.Services.CommentaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/commentaires")
public class CommentaireController {

    @Autowired
    private CommentaireService commentaireService;

    @Autowired
    UserRepository userRepository;
    @Autowired
    AnnonceCovRepository annonceCovRepository;

    @GetMapping("/all")
    public ResponseEntity<List<Commentaire>> getAllCommentaires() {
        List<Commentaire> commentaires = commentaireService.getAllCommentaires();
        return new ResponseEntity<>(commentaires, HttpStatus.OK);
    }

    @GetMapping("/{idco}")
    public ResponseEntity<Commentaire> getCommentaireById(@PathVariable("idco") Long idco) {
        Commentaire commentaire = commentaireService.getCommentaireById(idco);
        if (commentaire != null) {
            return new ResponseEntity<>(commentaire, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add/{userId}/{annonceCovId}")
    public ResponseEntity<Commentaire> saveCommentaire(@RequestBody Commentaire commentaire,
                                                       @PathVariable("userId") Long userId,
                                                       @PathVariable("annonceCovId") Long annonceCovId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        AnnonceCov annonceCov = annonceCovRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("annonce not found with id: " + annonceCovId));

        commentaire.setUser(user);
        commentaire.setAnnonceCov(annonceCov);

        Commentaire createdCommentaire = commentaireService.saveCommentaire(commentaire);
        return new ResponseEntity<>(createdCommentaire, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Commentaire> updateCommentaire(@PathVariable("idco") Long idco, @RequestBody Commentaire updatedCommentaire) {
        Commentaire updated = commentaireService.updateCommentaire(idco, updatedCommentaire);
        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{idco}")
    public ResponseEntity<Void> deleteCommentaire(@PathVariable("idco") Long idco) {
        commentaireService.deleteCommentaire(idco);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}