package com.easc01.disastermediaapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "video")
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String url;
    private String userId;

    @ManyToOne
    @JoinColumn(name = "disaster_id")
    private Disaster disaster;
}
