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
public class Local {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String adresse;
    private int nbr_chambres;
    private int superficie;
    @Enumerated(EnumType.STRING)
    private TypeLocal Type;
    @JsonIgnore
    @OneToMany(mappedBy = "local", cascade = CascadeType.ALL )
    private List<ReservationColoc> reservationColocs;

}