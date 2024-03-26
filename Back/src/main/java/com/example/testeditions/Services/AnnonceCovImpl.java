package com.example.testeditions.Services;

import com.example.testeditions.Entites.AnnonceCov;
import com.example.testeditions.Entites.User;
import com.example.testeditions.Entites.Voiture;
import com.example.testeditions.Repositories.AnnonceCovRepository;
import com.example.testeditions.Repositories.UserRepository;
import com.example.testeditions.Repositories.VoitureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnnonceCovImpl implements AnnonceCovService {

    @Autowired
    private AnnonceCovRepository annonceCovRepository;

    @Autowired
    private VoitureService voitureService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    VoitureRepository voitureRepository;

    @Autowired
    private UserService userService;

    @Override
    public List<AnnonceCov> getAllAnnonces() {
        return annonceCovRepository.findAll();
    }

    @Override
    public AnnonceCov getAnnonceById(Long ida) {

        return annonceCovRepository.findById(ida).orElseThrow(() -> new RuntimeException("AnnonceCov not found with id: " + ida));
    }

    @Override
    public AnnonceCov createAnnonceWithCircuitAndUserAndVoiture(AnnonceCov annonceCov) {

        User user = annonceCov.getUser();
        Voiture voiture = annonceCov.getVoiture();


        if (user == null || voiture == null ) {
            throw new RuntimeException("User or Voiture  null");
        }


        AnnonceCov createdAnnonce = annonceCovRepository.save(annonceCov);

        // Return the created annonceCov
        return createdAnnonce;
    }


    @Override
    public AnnonceCov updateAnnonce(Long ida, AnnonceCov updatedAnnonceCov) {
        Optional<AnnonceCov> annonceCovOptional = annonceCovRepository.findById(ida);
        AnnonceCov existingAnnonceCov = null;
        if (annonceCovOptional.isPresent()) {
            existingAnnonceCov = annonceCovOptional.get();
            existingAnnonceCov.setTitre(updatedAnnonceCov.getTitre());
            existingAnnonceCov.setDescription(updatedAnnonceCov.getDescription());
            existingAnnonceCov.setImage(updatedAnnonceCov.getImage());
            existingAnnonceCov.setPrix(updatedAnnonceCov.getPrix());
            existingAnnonceCov.setDateDepart(updatedAnnonceCov.getDateDepart());
            existingAnnonceCov.setPlacesDisponibles(updatedAnnonceCov.getPlacesDisponibles());
            existingAnnonceCov.setStatus(updatedAnnonceCov.getStatus());


            existingAnnonceCov.setPointDepart(updatedAnnonceCov.getPointDepart());
            existingAnnonceCov.setPointArrivee(updatedAnnonceCov.getPointArrivee());
            existingAnnonceCov.setPointStop(updatedAnnonceCov.getPointStop());
            existingAnnonceCov.setDistance(updatedAnnonceCov.getDistance());
        }

        return annonceCovRepository.save(existingAnnonceCov);
    }



    @Override
    public void deleteAnnonce(Long ida) {
        annonceCovRepository.deleteById(ida);
    }

    @Override
    public AnnonceCov saveAnnonce(Long userId, String matricule, AnnonceCov annonceCov) {
        User user = userService.getUserById(userId);
        Voiture voiture= voitureService.getVoitureByMatricule(matricule);
        annonceCov.setUser(user);
        annonceCov.setVoiture(voiture);
        return annonceCovRepository.save(annonceCov);
    }

    @Override
    public List<AnnonceCov> getAnnonceByUser(User user) {
        return annonceCovRepository.findByUser(user);
    }

    @Override
    public AnnonceCov addAnnonceCov(Long userId, String matricule, AnnonceCov annonceCov) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Voiture voiture = voitureRepository.findByUserAndMatricule(user, matricule)
                .orElseThrow(() -> new IllegalArgumentException("User does not own a car with this matricule"));

        annonceCov.setUser(user);
        annonceCov.setVoiture(voiture);
        return annonceCovRepository.save(annonceCov);
    }



}
