package com.backend.apibackendgob.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FeatureDTO {
    private Long id;
    private String title;
    private String description;
    private List<TaskDTO> tasks;
}