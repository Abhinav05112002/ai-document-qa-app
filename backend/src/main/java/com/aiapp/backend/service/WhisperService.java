package com.aiapp.backend.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.*;
import org.springframework.web.client.RestTemplate;

import com.aiapp.backend.ai.WhisperResponse;

@Service
public class WhisperService {

    @Value("${groq.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate =
            new RestTemplate();

    public WhisperResponse transcribeAudio(
            File audioFile) {

        String url =
            "https://api.groq.com/openai/v1/audio/transcriptions";

        HttpHeaders headers =
                new HttpHeaders();

        headers.setBearerAuth(apiKey);

        headers.setContentType(
                MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body =
                new LinkedMultiValueMap<>();

        body.add(
                "file",
                new FileSystemResource(audioFile)
        );

        body.add(
                "model",
                "whisper-large-v3"
        );
        
        body.add(
        	    "response_format",
        	    "verbose_json"
        	);

        HttpEntity<MultiValueMap<String, Object>>
                requestEntity =
                new HttpEntity<>(body, headers);

        ResponseEntity<WhisperResponse> response =
        		restTemplate.postForEntity(
                        url,
                        requestEntity,
                        WhisperResponse.class
                );

        return response.getBody();
    }
}
