package com.example.testeditions.Entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentLike implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idlike;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Commentaire commentaire;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}