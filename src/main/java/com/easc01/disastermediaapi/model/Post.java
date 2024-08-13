package com.easc01.disastermediaapi.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Builder
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String content;
    private String userId;
    private Instant publishedDate;

    @ManyToOne
    @JoinColumn(name = "disaster_id")
    private Disaster disaster;
}
