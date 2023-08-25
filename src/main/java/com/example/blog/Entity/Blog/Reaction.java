package com.example.blog.Entity.Blog;

import com.example.blog.Entity.ProUserEntity;
import com.example.blog.Entity.UserEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reactions")
public class Reaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;
    private ReactionType reactionType;
    @OneToOne
    private UserEntity user;
    @OneToOne
    private Blog blog;
    @OneToOne
    private Comment comment;
}
