package com.aiapp.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.aiapp.backend.service.SummaryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/summary")
@RequiredArgsConstructor
public class SummaryController {

    private final SummaryService
            summaryService;

    @PostMapping("/{fileId}")
    public ResponseEntity<String>
        generateSummary(
            @PathVariable Long fileId) {

        return ResponseEntity.ok(
                summaryService
                .generateSummary(fileId)
        );
    }
}