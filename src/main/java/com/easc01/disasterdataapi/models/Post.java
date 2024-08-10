package com.easc01.disasterdataapi.models;

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
