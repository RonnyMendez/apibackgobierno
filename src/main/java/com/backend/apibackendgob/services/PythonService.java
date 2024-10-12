package com.backend.apibackendgob.services;

import com.backend.apibackendgob.DTO.TaskDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class PythonService {

    private final RestTemplate restTemplate;
    private static final Logger logger = LoggerFactory.getLogger(PythonService.class);

    @Autowired
    public PythonService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public int getStoryPointForTask(TaskDTO task) {
        String url = "http://127.0.0.1:5000/predict";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Crear el body que coincide con el formato esperado por la API de Python
        Map<String, Object> requestBody = new LinkedHashMap<>();  // Usamos LinkedHashMap para mantener el orden
        requestBody.put("issueType", task.getIssueType());
        requestBody.put("sprint", task.getSprint());
        requestBody.put("summary", task.getSummary());
        requestBody.put("status", task.getStatus());
        requestBody.put("priority", task.getPriority());
        requestBody.put("commentCount", task.getCommentCount());
        requestBody.put("votes", task.getVotes());
        requestBody.put("blockedBy", task.getBlockedBy());
        requestBody.put("blocks", task.getBlocks());
        requestBody.put("dependedOnBy", task.getDependedOnBy());
        requestBody.put("dependedOn", task.getDependedOn());

        // Convertir el requestBody a JSON string y loguearlo
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonRequestBody = objectMapper.writeValueAsString(requestBody);
            logger.info("JSON enviado a la API de Python: {}", jsonRequestBody);
        } catch (Exception e) {
            logger.error("Error al convertir el requestBody a JSON", e);
        }

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        try {
            // Hacemos la petición POST a la API de Python
            ResponseEntity<Map> response = restTemplate.postForEntity(url, requestEntity, Map.class);

            // Verificamos si la respuesta contiene datos válidos
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                Map<String, Object> responseBody = response.getBody();
                return (int) responseBody.get("prediccion_storyPoint");
            } else {
                throw new RuntimeException("Error en la respuesta de la API de Python");
            }
        } catch (Exception e) {
            // Manejo de errores de la API
            throw new RuntimeException("Error al conectar con la API de Python: " + e.getMessage(), e);
        }
    }
}