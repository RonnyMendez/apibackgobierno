package com.backend.apibackendgob.services;

import com.backend.apibackendgob.DTO.FeatureDTO;
import com.backend.apibackendgob.DTO.TaskDTO;
import com.backend.apibackendgob.models.Feature;
import com.backend.apibackendgob.models.Task;
import com.backend.apibackendgob.repositories.FeatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeatureService {
    private final FeatureRepository featureRepository;

    @Autowired
    public FeatureService(FeatureRepository featureRepository) {
        this.featureRepository = featureRepository;
    }

    // Método para guardar el feature en la base de datos
    public FeatureDTO saveFeature(FeatureDTO featureDTO) {
        // Mapear DTO a entidad
        Feature feature = mapDTOToFeature(featureDTO);
        // Guardar en la base de datos
        Feature savedFeature = featureRepository.save(feature);
        // Mapear entidad guardada a DTO y retornar
        return mapFeatureToDTO(savedFeature);
    }

    public List<FeatureDTO> getAllFeatures() {
        List<Feature> features = featureRepository.findAll();
        return features.stream().map(this::mapFeatureToDTO).collect(Collectors.toList());
    }

    private Feature mapDTOToFeature(FeatureDTO featureDTO) {
        Feature feature = new Feature();
        feature.setTitle(featureDTO.getTitle());
        List<Task> tasks = featureDTO.getTasks().stream().map(taskDTO -> {
            Task task = new Task();
            task.setIssueType(taskDTO.getIssueType());
            // Mapear todos los campos de TaskDTO a Task
            task.setStoryPoint(taskDTO.getStoryPoint());
            task.setFeature(feature); // Establecer relación
            return task;
        }).collect(Collectors.toList());
        feature.setTasks(tasks);
        return feature;
    }

    private FeatureDTO mapFeatureToDTO(Feature feature) {
        FeatureDTO featureDTO = new FeatureDTO();
        featureDTO.setId(feature.getId());
        featureDTO.setTitle(feature.getTitle());
        List<TaskDTO> taskDTOs = feature.getTasks().stream().map(task -> {
            TaskDTO taskDTO = new TaskDTO();
            taskDTO.setId(task.getId());
            // Mapear todos los campos de Task a TaskDTO
            return taskDTO;
        }).collect(Collectors.toList());
        featureDTO.setTasks(taskDTOs);
        return featureDTO;
    }
}
