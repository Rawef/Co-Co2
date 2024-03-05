package com.example.testeditions.Controllers;


import com.example.testeditions.Entites.ReservationCov;
import com.example.testeditions.Services.ReservationCovService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ResrvationCovController {

    @Autowired
    ReservationCovService reservationCovService;


    @GetMapping("/retrieve-all-reservations")
    public ResponseEntity<List<ReservationCov>> getAllReservations() {
        List<ReservationCov> reservations = reservationCovService.getAllReservations();
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }
    @GetMapping("/retrieve-all-reservations/{idr}")
    public ResponseEntity<ReservationCov> getReservationById(@PathVariable Long idr) {
        ReservationCov reservation = reservationCovService.getReservationById(idr);
        if (reservation != null) {
            return new ResponseEntity<>(reservation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/make-reservation/{ida}/{id}")
    public ResponseEntity<ReservationCov> makeReservation(
            @PathVariable Long ida,
            @PathVariable Long id) {
        ReservationCov madeReservation = reservationCovService.createReservation(ida, id);
        if (madeReservation != null) {
            return new ResponseEntity<>(madeReservation, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update-reservation/{idr}")
    public ResponseEntity<ReservationCov> updateReservation(
            @PathVariable Long idr,
            @RequestBody ReservationCov updatedReservations) {
        ReservationCov reservation = reservationCovService.updateReservation(idr, updatedReservations);
        if (reservation != null) {
            return new ResponseEntity<>(reservation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/cancel-reservation/{idr}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long idr) {
        reservationCovService.deleteReservation(idr);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
