package com.easc01.disastermediaapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@Entity
@Table(name = "disaster")
public class Disaster {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true)
    private String recordId;

    private String title;
    private String summary;
    private String incidentLocation;
    private String incidentType;

    @OneToMany(mappedBy = "disaster", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Video> videos;  // video url

    @CreationTimestamp
    private Instant createdAt;
    private Instant updatedAt;
}
