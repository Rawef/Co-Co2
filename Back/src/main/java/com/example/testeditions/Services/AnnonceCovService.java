package com.example.testeditions.Services;

import com.example.testeditions.Entites.AnnonceCov;

import java.util.List;

public interface AnnonceCovService {

    List<AnnonceCov> getAllAnnonces();
    AnnonceCov getAnnonceById(Long ida);
    AnnonceCov createAnnonceWithCircuitAndUserAndVoiture(AnnonceCov annonceCov);
    AnnonceCov updateAnnonce(Long ida, AnnonceCov updatedAnnonceCov);
    void deleteAnnonce(Long ida);
}

