package com.main.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.main.servie.InvoiceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/integration")
@Slf4j
public class TelexController {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final InvoiceService invoiceService;

    public TelexController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping
    public String telexCron(@RequestBody String request) {
        log.info("Received request: {}", request);

        try {
            JsonNode jsonNode = objectMapper.readTree(request);
            JsonNode settingsNode = jsonNode.get("settings");

            if (settingsNode != null && settingsNode.isArray() && settingsNode.size() > 0) {
                JsonNode firstSetting = settingsNode.get(0);
                String newCronExpression = firstSetting.get("default").asText();

                log.info("Updating cron expression to: {}", newCronExpression);
                invoiceService.updateCronExpression(newCronExpression);

                return "Updated cron expression to: " + newCronExpression;
            }
        } catch (IOException e) {
            log.error("Error parsing JSON request", e);
        }

        return "Failed to update cron expression";
    }
}
