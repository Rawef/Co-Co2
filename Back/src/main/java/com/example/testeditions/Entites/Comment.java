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
@Table(name = "Comment")
public class Comment {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    private String content;
    @Temporal(TemporalType.DATE)
    private Date date_com;
    @Column(name = "likee")
    private int llike;
    @Column(name = "dislikee")
    private int dislike;
    @ManyToOne
    AnnonceColocation annoncecolocation;

}