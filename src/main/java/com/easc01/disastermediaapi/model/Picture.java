package com.easc01.disastermediaapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "picture")
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String url;
    private String userId;
    private Instant publishedDate;

    @ManyToOne
    @JoinColumn(name = "disaster_id")
    private Disaster disaster;
}
