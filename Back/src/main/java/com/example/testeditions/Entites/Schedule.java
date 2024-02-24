package com.example.testeditions.Entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idsch")
    private Long idsch;

    private Date day;
    private String pointdedepart ;
    private String area;
    private Date heurededepart ;
    private Date heurederetour ;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
