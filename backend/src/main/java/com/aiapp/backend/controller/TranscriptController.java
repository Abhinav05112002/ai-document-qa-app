package com.aiapp.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.aiapp.backend.entity.Transcript;
import com.aiapp.backend.repository.TranscriptRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/transcripts")
@RequiredArgsConstructor
public class TranscriptController {

    private final TranscriptRepository
            transcriptRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Transcript>
        getTranscript(
            @PathVariable Long id) {

        Transcript transcript =
                transcriptRepository
                .findById(id)
                .orElseThrow(() ->
                    new RuntimeException(
                        "Transcript not found"));

        return ResponseEntity.ok(transcript);
    }
}
