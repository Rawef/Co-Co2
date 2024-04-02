package com.example.testeditions.Services;

import com.example.testeditions.Entites.AnnonceCov;
import com.example.testeditions.Entites.ReservationCov;
import com.example.testeditions.Entites.User;

import java.util.List;

public interface ReservationCovService {

    List<ReservationCov> getAllReservations();
    ReservationCov getReservationById(Long idr);
    ReservationCov createReservation(Long ida, Long userId);
    ReservationCov updateReservation(Long idr, ReservationCov updatedReservations);
    void deleteReservation(Long idr);
   List<ReservationCov> getReservationByUser(User user);

    List<ReservationCov> getReservationByAnnonceCov(AnnonceCov annonceCov);

    boolean deleteReservationByUserId(Long userId, Long idr);

}
