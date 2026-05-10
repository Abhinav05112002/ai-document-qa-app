package com.aiapp.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.aiapp.backend.entity.ExtractedContent;
import com.aiapp.backend.repository.ExtractedContentRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/extracted")
@RequiredArgsConstructor
public class ExtractedContentController {

    private final ExtractedContentRepository
            extractedContentRepository;

    @GetMapping("/{id}")
    public ResponseEntity<ExtractedContent>
        getExtractedContent(
            @PathVariable Long id) {

        ExtractedContent content =
                extractedContentRepository
                .findById(id)
                .orElseThrow(() ->
                    new RuntimeException(
                        "Content not found"));

        return ResponseEntity.ok(content);
    }
}
