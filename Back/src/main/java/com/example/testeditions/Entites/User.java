package com.example.testeditions.Entites;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String nom ;
    private String email;
    private String password;
    private int telephone;
    private String rating;

    @Enumerated(EnumType.STRING)
    private TypeRole role;

    private Boolean connected = false;
    private boolean banned = false;


    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL )
    private List<Preferences> preferences;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL )
    private List<ReservationCov> reservations;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<AnnonceCov> annonceCov;



    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    private List<Reclamation> Reclamations;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
    private List<Post> Posts;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
    private List<Subscription> subscriptions;





}
