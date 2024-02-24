package com.example.testeditions.Repositories;

import com.example.testeditions.Entites.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findOneByEmailAndPassword(String email, String password);
    User findByEmail(String email);

    User findByNom(String username);

    @Transactional
    Long deleteByNom(String username);

    long countByBannedTrue();

    long count();


}
