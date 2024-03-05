package com.example.testeditions.Controllers;

import com.example.testeditions.Entites.Voiture;
import com.example.testeditions.Services.VoitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/voitures")
public class VoitureController {

    private final VoitureService voitureService;

    @Autowired
    public VoitureController(VoitureService voitureService) {
        this.voitureService = voitureService;
    }

    @GetMapping
    public ResponseEntity<List<Voiture>> getAllVoitures() {
        List<Voiture> voitures = voitureService.getAllVoitures();
        return new ResponseEntity<>(voitures, HttpStatus.OK);
    }

    @GetMapping("/{idv}")
    public ResponseEntity<Voiture> getVoitureById(@PathVariable("idv") Long idv) {
        Voiture voiture = voitureService.getVoitureById(idv);
        return new ResponseEntity<>(voiture, HttpStatus.OK);
    }

    @PostMapping("/add-voiture/{userId}")
    public ResponseEntity<Voiture> registerVoiture(@PathVariable("userId") Long userId, @RequestBody Voiture voiture) {
        Voiture createdVoiture = voitureService.saveVoiture(userId, voiture);
        return new ResponseEntity<>(createdVoiture, HttpStatus.CREATED);
    }

    @PutMapping("/update-voiture/{idv}")
    public ResponseEntity<Voiture> updateVoiture(@PathVariable("idv") Long idv, @RequestBody Voiture updatedVoiture) {
        Voiture updated = voitureService.updateVoiture(idv, updatedVoiture);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/delete-voiture/{idv}")
    public ResponseEntity<Void> deleteVoiture(@PathVariable("idv") Long idv) {
        voitureService.deleteVoiture(idv);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
