package com.example.testeditions.Repositories;

import com.example.testeditions.Entites.AnnonceCov;
import com.example.testeditions.Entites.ReservationCov;
import com.example.testeditions.Entites.User;
import com.example.testeditions.Entites.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationCovRepository extends JpaRepository<ReservationCov,Long> {

    List<ReservationCov> findByUser(User user);

    List<ReservationCov> findByAnnonceCov(AnnonceCov annonceCov);

    List<ReservationCov> findByAnnonceCov_Ida(Long ida);


}
