package com.example.testeditions.Entites;



import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class PostComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdComment;
    private  String DescriptionComment;
    private Date DateComment;
    @ManyToOne
    Post post;
    @OneToMany(cascade = CascadeType.ALL, mappedBy="postComment")
    private List<ResponseComment> ResponseComments;}
