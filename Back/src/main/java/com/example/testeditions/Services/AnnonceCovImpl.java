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
public class AnnonceCovImpl implements AnnonceCovService{

    @Autowired
    private AnnonceCovRepository annonceCovRepository;

    @Autowired
    private VoitureRepository voitureRepository;

    @Autowired
    private CircuitRepository circuitRepository;
    @Autowired
    private UserRepository userRepository;

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
        Circuit circuit = annonceCov.getCircuit();


        if (user == null || voiture == null || circuit ==null) {
            throw new RuntimeException("User or Voiture or circuit null");
        }


        AnnonceCov createdAnnonce = annonceCovRepository.save(annonceCov);

        // Return the created annonceCov
        return createdAnnonce;
    }



    @Override
    public AnnonceCov updateAnnonce(Long ida, AnnonceCov updatedAnnonceCov) {
        Optional<AnnonceCov> annonceCovOptional = annonceCovRepository.findById(ida);
        if (annonceCovOptional.isPresent()) {
            AnnonceCov existingAnnonceCov = annonceCovOptional.get();
            existingAnnonceCov.setTitre(updatedAnnonceCov.getTitre());
            existingAnnonceCov.setDescription(updatedAnnonceCov.getDescription());
            existingAnnonceCov.setImage(updatedAnnonceCov.getImage());
            existingAnnonceCov.setPrix(updatedAnnonceCov.getPrix());
            existingAnnonceCov.setDateDepart(updatedAnnonceCov.getDateDepart());
            existingAnnonceCov.setPlacesDisponibles(updatedAnnonceCov.getPlacesDisponibles());
            existingAnnonceCov.setStatus(updatedAnnonceCov.getStatus());

            Circuit updatedCircuit = updatedAnnonceCov.getCircuit();
            if (updatedCircuit != null) {
                Circuit existingCircuit = existingAnnonceCov.getCircuit();
                existingCircuit.setPointDepart(updatedCircuit.getPointDepart());
                existingCircuit.setPointArrivee(updatedCircuit.getPointArrivee());
                existingCircuit.setPointStop(updatedCircuit.getPointStop());
                existingCircuit.setDistance(updatedCircuit.getDistance());
            }

            return annonceCovRepository.save(existingAnnonceCov);
        }
        return null;
    }

    @Override
    public void deleteAnnonce(Long ida) {
        annonceCovRepository.deleteById(ida);
    }
}
