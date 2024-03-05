package com.example.testeditions.Services;

import com.example.testeditions.Entites.AnnonceCov;
import com.example.testeditions.Entites.ReservationCov;
import com.example.testeditions.Entites.User;
import com.example.testeditions.Repositories.AnnonceCovRepository;
import com.example.testeditions.Repositories.ReservationCovRepository;
import com.example.testeditions.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReservationCovImpl implements ReservationCovService {

    @Autowired
    private ReservationCovRepository reservationCovRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AnnonceCovRepository annonceCovRepository;

    @Override
    public List<ReservationCov> getAllReservations() {
        return reservationCovRepository.findAll();
    }

    @Override
    public ReservationCov getReservationById(Long idr) {
        return reservationCovRepository.findById(idr).orElse(null);
    }

    @Override
    public ReservationCov createReservation(Long ida, Long id) {
        AnnonceCov annonceCov = annonceCovRepository.findById(ida).orElse(null);
        User user = userRepository.findById(id).orElse(null);
        if (annonceCov != null && user != null && annonceCov.getPlacesDisponibles() > 0) {
            ReservationCov reservation = new ReservationCov();
            reservation.setUser(user);
            reservation.setAnnonceCov(annonceCov);
            reservation.setReservationTime(new Date());

            annonceCov.setPlacesDisponibles(annonceCov.getPlacesDisponibles() - 1);
            annonceCovRepository.save(annonceCov);
            return reservationCovRepository.save(reservation);
        }

        return null;
    }


    @Override
    public ReservationCov updateReservation(Long idr, ReservationCov updatedReservations) {
        ReservationCov existingReservations = reservationCovRepository.findById(idr).orElse(null);

        if (existingReservations != null) {
            existingReservations.setReservationTime(updatedReservations.getReservationTime());

            return reservationCovRepository.save(existingReservations);
        }

        return null;
    }


    @Override
    public void deleteReservation(Long idr) {
        ReservationCov reservation = reservationCovRepository.findById(idr).orElse(null);

        if (reservation != null) {
            AnnonceCov annonceCov = reservation.getAnnonceCov();

            annonceCov.setPlacesDisponibles(annonceCov.getPlacesDisponibles() + 1);
            annonceCovRepository.save(annonceCov);
            reservationCovRepository.deleteById(idr);
        }
    }
}
