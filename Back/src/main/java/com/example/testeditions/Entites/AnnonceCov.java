package com.example.testeditions.Entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnnonceCov implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ida")
    private Long ida;


    private String titre;
    private String description;
    private String image;
    private float prix;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDepart;
    private int placesDisponibles;
    private int contact;
    private String status;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    private Circuit circuit;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "voiture_id")
    private Voiture voiture;



    @OneToMany(mappedBy = "annonceCov")
    private List<ReservationCov> reservations;
}