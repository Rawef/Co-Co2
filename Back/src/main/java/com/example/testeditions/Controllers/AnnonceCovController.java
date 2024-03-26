package com.example.testeditions.Controllers;


import com.example.testeditions.DTO.AnnonceCovRequestDto;
import com.example.testeditions.Entites.AnnonceCov;
import com.example.testeditions.Entites.User;
import com.example.testeditions.Entites.Voiture;
import com.example.testeditions.Repositories.UserRepository;
import com.example.testeditions.Repositories.VoitureRepository;
import com.example.testeditions.Services.AnnonceCovService;
import com.example.testeditions.Services.UserService;
import com.example.testeditions.Services.VoitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/annonces")
public class AnnonceCovController {

    @Autowired
    private AnnonceCovService annonceCovService;
    @Autowired
    VoitureRepository voitureRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private VoitureService voitureService;




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



    @GetMapping("/user/{userId}")
    public List<AnnonceCov> getAnnonceByUser(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        return annonceCovService.getAnnonceByUser(user);
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




    @PostMapping("/add/{userId}/{matricule}")
    public ResponseEntity<?> addAnnonceCov(@PathVariable Long userId,
                                           @PathVariable String matricule,
                                           @RequestBody AnnonceCov annonceCov) {
        try {
            // Retrieve the user
            User user = userService.getUserById(userId);
            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }

            // Retrieve the voiture by matricule and user
            Voiture voiture = voitureService.getVoitureByUserAndMatricule(user, matricule);
            if (voiture == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Voiture not found or not owned by user");
            }

            // Add the annonceCov
            AnnonceCov addedAnnonceCov = annonceCovService.addAnnonceCov(userId, matricule, annonceCov);
            return ResponseEntity.status(HttpStatus.CREATED).body(addedAnnonceCov);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("An error occurred: " + e.getMessage());
        }
    }

}

