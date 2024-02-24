package com.example.testeditions.Entites;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Reponse {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id_reponse;

    private String description_reponse;
    private LocalDateTime date_reponse;
}
