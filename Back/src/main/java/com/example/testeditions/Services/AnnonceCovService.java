package com.example.testeditions.Services;

import com.example.testeditions.Entites.AnnonceCov;
import com.example.testeditions.Entites.User;
import com.example.testeditions.Entites.Voiture;

import java.util.List;

public interface AnnonceCovService {

    List<AnnonceCov> getAllAnnonces();
    AnnonceCov getAnnonceById(Long ida);
    AnnonceCov createAnnonceWithCircuitAndUserAndVoiture(AnnonceCov annonceCov);
    AnnonceCov updateAnnonce(Long ida, AnnonceCov updatedAnnonceCov);
    void deleteAnnonce(Long ida);

    AnnonceCov saveAnnonce(Long userId,String matricule, AnnonceCov annonceCov);

    List<AnnonceCov> getAnnonceByUser(User user);

    AnnonceCov addAnnonceCov(Long userId, String matricule, AnnonceCov annonceCov);

}

