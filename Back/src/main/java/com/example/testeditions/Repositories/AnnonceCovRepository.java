package com.example.testeditions.Repositories;

import com.example.testeditions.Entites.AnnonceCov;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnnonceCovRepository extends JpaRepository<AnnonceCov,Long> {

    @Query("SELECT c FROM AnnonceCov c LEFT JOIN FETCH c.reservations WHERE c.ida = :idr")
    Optional<AnnonceCov> findByIdWithReservations(@Param("idr") Long idr);


}
