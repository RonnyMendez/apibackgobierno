package com.backend.apibackendgob.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "issueType") // Asegurando el mapeo explícito
    private int issueType;

    @Column(name = "sprint")
    private int sprint;

    @Column(name = "summary")
    private String summary;

    @Column(name = "status")
    private int status;

    @Column(name = "priority")
    private int priority;

    @Column(name = "commentCount")
    private int commentCount;

    @Column(name = "votes")
    private int votes;

    @Column(name = "blockedBy") // Asegurando el mapeo explícito
    private int blockedBy;

    @Column(name = "blocks")
    private int blocks;

    @Column(name = "dependedOnBy") // Asegurando el mapeo explícito
    private int dependedOnBy;

    @Column(name = "dependedOn") // Asegurando el mapeo explícito
    private int dependedOn;

    @Column(name = "storyPoint")
    private int storyPoint;

    @ManyToOne
    @JoinColumn(name = "feature_id")
    private Feature feature;

}