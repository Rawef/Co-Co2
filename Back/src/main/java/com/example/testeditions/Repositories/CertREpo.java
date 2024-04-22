package com.example.testeditions.Repositories;


import com.example.testeditions.Entites.Certification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertREpo extends JpaRepository<Certification,Long> {

    Certification findByUserId(Long userId);
}
