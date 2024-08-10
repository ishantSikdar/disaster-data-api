package com.easc01.disasterdataapi.models;

import jakarta.persistence.*;

@Entity
@Table(name = "api_key")
public class ApiKey {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;
    private String email;

    @Column(unique = true)
    private String key;
}
