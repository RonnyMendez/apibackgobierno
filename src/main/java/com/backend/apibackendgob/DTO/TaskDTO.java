package com.backend.apibackendgob.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TaskDTO {
    private Long id;
    private int issueType;
    private int sprint;
    private String summary;
    private int status;
    private int priority;
    private int commentCount;
    private int votes;
    private int blockedBy;
    private int blocks;
    private int dependedOnBy;
    private int dependedOn;
    private int storyPoint; // Este campo se llenará después de llamar a la API de Python
}
