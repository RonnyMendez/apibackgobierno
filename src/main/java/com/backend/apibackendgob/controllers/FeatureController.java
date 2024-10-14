package com.backend.apibackendgob.controllers;


import java.util.List;

import com.backend.apibackendgob.DTO.FeatureDTO;
import com.backend.apibackendgob.DTO.TaskDTO;
import com.backend.apibackendgob.services.FeatureService;
import com.backend.apibackendgob.services.PythonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/features")
public class FeatureController {

     private final FeatureService featureService;
    private final PythonService pythonService;

    @Autowired
    public FeatureController(FeatureService featureService, PythonService pythonService) {
        this.featureService = featureService;
        this.pythonService = pythonService;
    }

    // Endpoint para calcular story points
    @PostMapping("/calculate-story-points")
    public ResponseEntity<FeatureDTO> calculateStoryPoints(@RequestBody FeatureDTO featureDTO) {
        for (TaskDTO task : featureDTO.getTasks()) {
            int storyPoint = pythonService.getStoryPointForTask(task);
            task.setStoryPoint(storyPoint);
        }
        return ResponseEntity.ok(featureDTO);
    }

    // Endpoint para guardar el feature
    @PostMapping("/save")
    public ResponseEntity<FeatureDTO> saveFeature(@RequestBody FeatureDTO featureDTO) {
        FeatureDTO savedFeature = featureService.saveFeature(featureDTO);
        return ResponseEntity.ok(savedFeature);
    }

}
