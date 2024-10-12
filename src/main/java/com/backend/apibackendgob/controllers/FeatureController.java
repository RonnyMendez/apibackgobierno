package com.backend.apibackendgob.controllers;


import java.util.List;

import com.backend.apibackendgob.DTO.FeatureDTO;
import com.backend.apibackendgob.DTO.TaskDTO;
import com.backend.apibackendgob.services.PythonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/features")
public class FeatureController {

    private final PythonService pythonService;

    @Autowired
    public FeatureController(PythonService pythonService) {
        this.pythonService = pythonService;
    }

    @PostMapping("/create")
    public ResponseEntity<FeatureDTO> createFeature(@RequestBody FeatureDTO feature) {
        List<TaskDTO> tasks = feature.getTasks();

        for (TaskDTO task : tasks) {
            // Llamada al servicio de Python para obtener el story point
            int storyPoint = pythonService.getStoryPointForTask(task);
            task.setStoryPoint(storyPoint);  // Asignamos el story point retornado
        }

        // Aquí podrías guardar el Feature y las Tasks en la base de datos si lo deseas

        // Devolvemos el feature con las tasks actualizadas
        return ResponseEntity.ok(feature);
    }
}
