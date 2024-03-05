package com.example.testeditions.Services;

import com.example.testeditions.Entites.Voiture;

import java.util.List;

public interface VoitureService {
    Voiture saveVoiture(Long userId, Voiture voiture);
    Voiture updateVoiture(Long idv, Voiture updatedVoiture);
    Voiture getVoitureById(Long idv);
    void deleteVoiture(Long idv);
    List<Voiture> getAllVoitures();
}


