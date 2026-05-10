package com.aiapp.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.aiapp.backend.entity.*;
import com.aiapp.backend.repository.*;
import com.aiapp.backend.service.TimestampSearchService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/timestamps")
@RequiredArgsConstructor
public class TimestampController {

    private final UploadedFileRepository
            uploadedFileRepository;

    private final TranscriptRepository
            transcriptRepository;

    private final TimestampSearchService
            timestampSearchService;

    @GetMapping("/{fileId}")
    public ResponseEntity<TranscriptSegment>
        searchTimestamp(
            @PathVariable Long fileId,
            @RequestParam String query) {

        UploadedFile uploadedFile =
                uploadedFileRepository
                .findById(fileId)
                .orElseThrow(() ->
                    new RuntimeException(
                        "File not found"));

        Transcript transcript =
                transcriptRepository
                .findByUploadedFile(uploadedFile)
                .orElseThrow(() ->
                    new RuntimeException(
                        "Transcript not found"));

        TranscriptSegment segment =
                timestampSearchService
                .searchTopic(
                    transcript,
                    query
                );

        return ResponseEntity.ok(segment);
    }
}
