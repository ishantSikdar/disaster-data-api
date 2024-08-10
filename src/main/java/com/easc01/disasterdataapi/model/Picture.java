package com.easc01.disasterdataapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "picture")
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String url;

    @ManyToOne
    @JoinColumn(name = "disaster_id")
    private Disaster disaster;
}
