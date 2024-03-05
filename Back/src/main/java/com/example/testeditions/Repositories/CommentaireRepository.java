package com.example.testeditions.Repositories;

import com.example.testeditions.Entites.Commentaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentaireRepository extends JpaRepository<Commentaire,Long > {
}
