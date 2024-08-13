package com.easc01.disastermediaapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String content;
    private String userId;

    @ManyToOne
    @JoinColumn(name = "disaster_id")
    private Disaster disaster;
}
