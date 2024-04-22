package com.example.testeditions.Entites;



import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Likee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdPost;
    private  StateLike stateLike;
    @ManyToOne
    Post post;
}