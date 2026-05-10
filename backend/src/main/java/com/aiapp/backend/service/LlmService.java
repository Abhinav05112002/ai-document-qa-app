package com.aiapp.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.aiapp.backend.ai.LlmRequest;
import com.aiapp.backend.ai.LlmResponse;

@Service
public class LlmService {

    @Value("${groq.api.key}")
    private String apiKey;

    @Value("${groq.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate =
            new RestTemplate();

    public String generateSummary(String text) {

        String prompt = """
                Summarize the following document
                in concise bullet points:

                """ + text;

        LlmRequest request =
                LlmRequest.builder()
                .model("llama-3.1-8b-instant")
                .messages(
                    List.of(
                        LlmRequest.Message.builder()
                        .role("user")
                        .content(prompt)
                        .build()
                    )
                )
                .build();

        HttpHeaders headers =
                new HttpHeaders();

        headers.setBearerAuth(apiKey);

        headers.setContentType(
                MediaType.APPLICATION_JSON);

        HttpEntity<LlmRequest> entity =
                new HttpEntity<>(request, headers);

        ResponseEntity<LlmResponse> response =
                restTemplate.postForEntity(
                    apiUrl,
                    entity,
                    LlmResponse.class
                );

        return response.getBody()
                .getChoices()
                .get(0)
                .getMessage()
                .getContent();
    }
    
    public String generateAnswer(
            String prompt) {

        LlmRequest request =
                LlmRequest.builder()
                .model("llama-3.1-8b-instant")
                .messages(
                    List.of(
                        LlmRequest.Message.builder()
                        .role("user")
                        .content(prompt)
                        .build()
                    )
                )
                .build();

        HttpHeaders headers =
                new HttpHeaders();

        headers.setBearerAuth(apiKey);

        headers.setContentType(
                MediaType.APPLICATION_JSON);

        HttpEntity<LlmRequest> entity =
                new HttpEntity<>(request, headers);

        ResponseEntity<LlmResponse> response =
                restTemplate.postForEntity(
                    apiUrl,
                    entity,
                    LlmResponse.class
                );

        return response.getBody()
                .getChoices()
                .get(0)
                .getMessage()
                .getContent();
    }
}