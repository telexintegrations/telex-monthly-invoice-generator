package com.main.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.List;
import java.util.HashMap;

@RestController
@RequestMapping("/integration.json")
@Slf4j
public class IntegrationJson {

    private final ObjectMapper objectMapper = new ObjectMapper(); // Jackson ObjectMapper

    @GetMapping
    public Map<String, Object> telexJson() {
        try {
            // Load the JSON file as an InputStream from classpath
            InputStream inputStream = new ClassPathResource("IntegratedJson.json").getInputStream();

            // Convert JSON InputStream to a Map
            return objectMapper.readValue(inputStream, Map.class);
        } catch (IOException e) {
            log.error("Error reading IntegratedJson.json file", e);
            return Map.of("error", "Failed to load JSON file");
        }
    }
}
