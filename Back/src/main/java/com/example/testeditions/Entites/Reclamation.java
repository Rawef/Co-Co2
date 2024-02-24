package com.example.testeditions.Entites;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Reclamation {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id_reclamation;
    private int id_utilisateur;
    private String categorie_reclamation;
    private String objet_reclamation;
    private String description_reclamation;
    private int etat_reclamation;
    private Date date_reclamation;
    @OneToOne
    private Reponse reponse;
}
