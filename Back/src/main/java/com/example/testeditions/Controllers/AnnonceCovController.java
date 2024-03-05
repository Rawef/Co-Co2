package com.example.testeditions.Controllers;


import com.example.testeditions.Entites.AnnonceCov;
import com.example.testeditions.Entites.User;
import com.example.testeditions.Entites.Voiture;
import com.example.testeditions.Repositories.UserRepository;
import com.example.testeditions.Repositories.VoitureRepository;
import com.example.testeditions.Services.AnnonceCovService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/annonces")
public class AnnonceCovController {

    @Autowired
    private AnnonceCovService annonceCovService;
    @Autowired
    VoitureRepository voitureRepository;
    @Autowired
    private UserRepository userRepository;


    @GetMapping("/retrieve-all-annonces")
    public ResponseEntity<List<AnnonceCov>> getAllAnnonces() {
        List<AnnonceCov> annonces = annonceCovService.getAllAnnonces();
        return new ResponseEntity<>(annonces, HttpStatus.OK);
    }

    @GetMapping("retrieve-annonce/{ida}")
    public ResponseEntity<AnnonceCov> getAnnonceById(@PathVariable("ida") Long ida) {
        AnnonceCov annonce = annonceCovService.getAnnonceById(ida);
        if (annonce != null) {
            return new ResponseEntity<>(annonce, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/annonce/{userId}/{voitureId}")
    public ResponseEntity<AnnonceCov> addAnnonceWithCircuitAndUserAndVoiture(
            @RequestBody AnnonceCov annonceCov,
            @PathVariable("userId") Long userId,
            @PathVariable("voitureId") Long voitureId)
            {


        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        Voiture voiture = voitureRepository.findById(voitureId)
                .orElseThrow(() -> new RuntimeException("Voiture not found with id: " + voitureId));



        annonceCov.setUser(user);
        annonceCov.setVoiture(voiture);

        AnnonceCov createdAnnonce = annonceCovService.createAnnonceWithCircuitAndUserAndVoiture(annonceCov);

        return new ResponseEntity<>(createdAnnonce, HttpStatus.CREATED);
    }




    @PutMapping("update-annonce/{ida}")
    public ResponseEntity<AnnonceCov> updateAnnonce(@PathVariable("ida") Long ida, @RequestBody AnnonceCov updatedAnnonceCov) {
        AnnonceCov updatedAnnonce = annonceCovService.updateAnnonce(ida, updatedAnnonceCov);
        if (updatedAnnonce != null) {
            return new ResponseEntity<>(updatedAnnonce, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("remove-annonce/{ida}")
    public ResponseEntity<Void> deleteAnnonce(@PathVariable("ida") Long ida) {
        annonceCovService.deleteAnnonce(ida);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
