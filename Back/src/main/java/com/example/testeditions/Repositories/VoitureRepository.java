package com.example.testeditions.Repositories;

import com.example.testeditions.Entites.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoitureRepository extends JpaRepository<Voiture,Long> {
}
