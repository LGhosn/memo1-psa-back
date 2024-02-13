package com.example.support_management.model;

import jakarta.persistence.*;

@Entity
@Table(name = "VERSIONS")
public class Version {
    @Id
    @Column(name = "ID_VERSION")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(name = "NAME")
    public String name;
}
