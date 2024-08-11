package com.easc01.disastermediaapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "disaster_id")
    private Disaster disaster;
}
