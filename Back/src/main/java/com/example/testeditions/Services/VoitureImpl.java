package com.example.testeditions.Services;

import com.example.testeditions.Entites.User;
import com.example.testeditions.Entites.Voiture;
import com.example.testeditions.Repositories.VoitureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoitureImpl implements VoitureService{

    @Autowired
     VoitureRepository voitureRepository;

    @Autowired
    UserService userService;


    @Override
    public List<Voiture> getAllVoitures() {
        return voitureRepository.findAll();
    }

    @Override
    public Voiture getVoitureById(Long idv) {
        Optional<Voiture> voitureOptional = voitureRepository.findById(idv);
        return voitureOptional.orElseThrow(() -> new RuntimeException("Voiture not found with id: " + idv));
    }



    @Override
    public Voiture saveVoiture(Long userId, Voiture voiture) {
        User user = userService.getUserById(userId);
        voiture.setUser(user);
        return voitureRepository.save(voiture);
    }

    @Override
        public Voiture updateVoiture(Long idv, Voiture updatedVoiture) {
        Optional<Voiture> voitureOptional = voitureRepository.findById(idv);
        if (voitureOptional.isPresent()) {
            Voiture existingVoiture = voitureOptional.get();
            existingVoiture.setNombrePlaces(updatedVoiture.getNombrePlaces());
            existingVoiture.setMatricule(updatedVoiture.getMatricule());

            return voitureRepository.save(existingVoiture);
        }
        return null;
    }

    @Override
    public void deleteVoiture(Long idv) {
        voitureRepository.deleteById(idv);
    }
}
