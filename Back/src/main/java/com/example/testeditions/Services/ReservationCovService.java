package com.example.testeditions.Services;

import com.example.testeditions.Entites.ReservationCov;

import java.util.List;

public interface ReservationCovService {

    List<ReservationCov> getAllReservations();
    ReservationCov getReservationById(Long idr);
    ReservationCov createReservation(Long ida, Long id);
    ReservationCov updateReservation(Long idr, ReservationCov updatedReservations);
    void deleteReservation(Long idr);

}
