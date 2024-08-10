package com.easc01.disasterdataapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "video")
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String url;

    @ManyToOne
    @JoinColumn(name = "disaster_id")
    private Disaster disaster;
}
