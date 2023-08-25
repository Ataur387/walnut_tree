package com.example.blog.Entity.Blog;

import com.example.blog.Entity.ProUserEntity;
import com.example.blog.Entity.UserEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "blogs")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String description;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;
    private BlogStatus blogStatus;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private UserEntity creator;
    @OneToMany(mappedBy = "blog")
    private List<Reaction> reactions;
    @OneToMany(mappedBy = "blog")
    private List<Comment> comments;
}
