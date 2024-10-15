package com.backend.apibackendgob.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "issueType", nullable = false)
    private int issueType;

    @Column(name = "sprint", nullable = false)
    private int sprint;

    @Column(name = "summary", nullable = false)
    private String summary;

    @Column(name = "status", nullable = false)
    private int status;

    @Column(name = "priority", nullable = false)
    private int priority;

    @Column(name = "commentCount", nullable = false)
    private int commentCount;

    @Column(name = "votes", nullable = false)
    private int votes;

    @Column(name = "blockedBy")
    private int blockedBy;

    @Column(name = "blocks")
    private int blocks;

    @Column(name = "dependedOnBy")
    private int dependedOnBy;

    @Column(name = "dependedOn")
    private int dependedOn;

    @Column(name = "storyPoint")
    private int storyPoint;

    @ManyToOne
    @JoinColumn(name = "feature_id")
    private Feature feature;

}