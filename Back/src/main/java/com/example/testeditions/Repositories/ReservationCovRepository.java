package com.example.testeditions.Repositories;

import com.example.testeditions.Entites.ReservationCov;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationCovRepository extends JpaRepository<ReservationCov,Long> {

}
