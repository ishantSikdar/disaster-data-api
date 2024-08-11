package com.easc01.disastermediaapi.model;

import jakarta.persistence.*;
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

    private String title;
    private String summary;

    @OneToMany(mappedBy = "disaster", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Post> posts;

    @OneToMany(mappedBy = "disaster", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Video> videos;

    @OneToMany(mappedBy = "disaster", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Picture> pictures;

    private List<String> tags;
    private List<String> postIds;
    private List<String> byUsers;

    @CreationTimestamp
    private Instant createdAt;
}
