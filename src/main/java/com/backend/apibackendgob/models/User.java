package com.backend.apibackendgob.models;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "users")  // Asume que la tabla se llama 'users'
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;


}