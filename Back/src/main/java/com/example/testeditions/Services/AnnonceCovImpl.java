package com.example.testeditions.Services;

import com.example.testeditions.Entites.*;
import com.example.testeditions.Repositories.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AnnonceCovImpl implements AnnonceCovService {

    @Autowired
    private AnnonceCovRepository annonceCovRepository;

    @Autowired
    private VoitureService voitureService;

    CommentaireRepository commentaireRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    ReservationCovRepository reservationCovRepository;
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
        }


        AnnonceCov createdAnnonce = annonceCovRepository.save(annonceCov);

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


    /*@Transactional
    public void deleteAnnonceByUserIdAndIda(Long userId, Long ida)  {
        AnnonceCov annonceCov = annonceCovRepository.findByUserIdAndIda(ida, userId);
        if (annonceCov == null) {
            throw new EntityNotFoundException("AnnonceCov not found for user with id: " + userId);
        }


        List<ReservationCov> reservations = annonceCov.getReservations();
        LocalDateTime now = LocalDateTime.now();
        boolean hasRecentReservations = false;
        for (ReservationCov reservation : reservations) {
            LocalDateTime reservationTime = reservation.getReservationTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            if (ChronoUnit.HOURS.between(reservationTime, now) < 48) {
                hasRecentReservations = true;
            } else {
                List<Commentaire> commentaires = commentaireRepository.findByAnnonceCov_Ida(ida);
                for (Commentaire commentaire : commentaires) {
                    List<CommentLike> likes = commentaire.getLikes();
                    likes.clear();

                    List<CommentDislike> dislikes = commentaire.getDislikes();
                    dislikes.clear();

                    commentaireRepository.delete(commentaire);
                }
                reservationCovRepository.delete(reservation);
            }
        }

        if (hasRecentReservations) {
            throw new IllegalStateException("Cannot delete AnnonceCov because there are reservations made less than 48 hours ago");
        } else {
            annonceCovRepository.delete(annonceCov);
        }
    }*/


   @Override
    public void deleteAnnonceByUserIdAndIda(Long userId, Long ida)  {
        AnnonceCov annonceCov = annonceCovRepository.findByUserIdAndIda( userId,ida);


        List<ReservationCov> reservations = annonceCov.getReservations();

        LocalDateTime now = LocalDateTime.now();
        boolean hasRecentReservations = false;
        for (ReservationCov reservation : reservations) {
            LocalDateTime reservationTime = reservation.getReservationTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            if (ChronoUnit.HOURS.between(reservationTime, now) < 48) {
                hasRecentReservations = true;
            } else {

                reservationCovRepository.delete(reservation);
            }
        }

        if (hasRecentReservations) {
            throw new IllegalStateException("Cannot delete AnnonceCov because there are reservations made less than 48 hours ago");
        } else {
            annonceCovRepository.delete(annonceCov);
        }
    }


    @Override
    public boolean deleteOldReservations(Long ida) {
        List<ReservationCov> reservations = reservationCovRepository.findByAnnonceCov_Ida(ida);

        System.out.println("Total reservations for annonce with ID " + ida + ": " + reservations.size());

        LocalDateTime now = LocalDateTime.now();
        boolean deleted = false;
        for (ReservationCov reservation : reservations) {
            LocalDateTime reservationTime = reservation.getReservationTime()
                    .toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
            if (ChronoUnit.HOURS.between(reservationTime, now) >= 48) {
                reservationCovRepository.delete(reservation);
                deleted = true;
                System.out.println("Deleted reservation ID " + reservation.getIdr() + " for annonce with ID " + ida);
            }
        }

        return deleted;
    }


}









