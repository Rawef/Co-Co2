package com.example.testeditions.Repositories;

import com.example.testeditions.Entites.AnnonceCov;
import com.example.testeditions.Entites.Commentaire;
import com.example.testeditions.Entites.User;
import com.example.testeditions.Entites.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnnonceCovRepository extends JpaRepository<AnnonceCov,Long> {

    @Query("SELECT c FROM AnnonceCov c LEFT JOIN FETCH c.reservations WHERE c.ida = :idr")
    Optional<AnnonceCov> findByIdWithReservations(@Param("idr") Long idr);

    List<AnnonceCov> findByUser(User user);

    @Modifying
    @Query("DELETE FROM AnnonceCov a WHERE a.ida = :ida AND a.user.id = :userId")
    void deleteByIdaAndUserId(@Param("ida") Long ida, @Param("userId") Long userId);


     AnnonceCov findByUserIdAndIda(Long userId, Long ida);


    long countByStatus(String status);


}
