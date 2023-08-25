package com.example.blog.Entity.Blog;

import com.example.blog.Entity.ProUserEntity;
import com.example.blog.Entity.UserEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String description;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private UserEntity commenter;
    @OneToOne
    @JoinColumn(name = "blog", referencedColumnName = "id")
    private Blog blog;
    @OneToMany(mappedBy = "comment")
    private List<Reaction> reactions;
}
