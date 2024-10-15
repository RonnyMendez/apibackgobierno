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
        feature.setDescription(featureDTO.getDescription());  // Asegúrate de mapear también la descripción del feature

        List<Task> tasks = featureDTO.getTasks().stream().map(taskDTO -> {
            Task task = new Task();
            task.setIssueType(taskDTO.getIssueType());
            task.setSprint(taskDTO.getSprint());
            task.setSummary(taskDTO.getSummary());  // Este campo es crucial, debe ser mapeado
            task.setStatus(taskDTO.getStatus());
            task.setPriority(taskDTO.getPriority());
            task.setCommentCount(taskDTO.getCommentCount());
            task.setVotes(taskDTO.getVotes());
            task.setBlockedBy(taskDTO.getBlockedBy());
            task.setBlocks(taskDTO.getBlocks());
            task.setDependedOnBy(taskDTO.getDependedOnBy());
            task.setDependedOn(taskDTO.getDependedOn());
            task.setStoryPoint(taskDTO.getStoryPoint());

            task.setFeature(feature); // Establecer la relación con el feature
            return task;
        }).collect(Collectors.toList());

        feature.setTasks(tasks);
        return feature;
    }

    private FeatureDTO mapFeatureToDTO(Feature feature) {
        FeatureDTO featureDTO = new FeatureDTO();
        featureDTO.setId(feature.getId());
        featureDTO.setTitle(feature.getTitle());
        featureDTO.setDescription(feature.getDescription()); // Mapear la descripción

        List<TaskDTO> taskDTOs = feature.getTasks().stream().map(task -> {
            TaskDTO taskDTO = new TaskDTO();
            taskDTO.setId(task.getId());
            taskDTO.setIssueType(task.getIssueType());
            taskDTO.setSprint(task.getSprint());
            taskDTO.setSummary(task.getSummary());  // Mapear el campo summary de vuelta al DTO
            taskDTO.setStatus(task.getStatus());
            taskDTO.setPriority(task.getPriority());
            taskDTO.setCommentCount(task.getCommentCount());
            taskDTO.setVotes(task.getVotes());
            taskDTO.setBlockedBy(task.getBlockedBy());
            taskDTO.setBlocks(task.getBlocks());
            taskDTO.setDependedOnBy(task.getDependedOnBy());
            taskDTO.setDependedOn(task.getDependedOn());
            taskDTO.setStoryPoint(task.getStoryPoint());
            return taskDTO;
        }).collect(Collectors.toList());

        featureDTO.setTasks(taskDTOs);
        return featureDTO;
    }
}
