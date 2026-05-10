package com.aiapp.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aiapp.backend.entity.Transcript;
import com.aiapp.backend.entity.TranscriptSegment;
import com.aiapp.backend.repository.TranscriptSegmentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TimestampSearchService {

    private final TranscriptSegmentRepository
            transcriptSegmentRepository;

    public TranscriptSegment searchTopic(
            Transcript transcript,
            String query) {

        List<TranscriptSegment> segments =
                transcriptSegmentRepository
                .findByTranscript(transcript);

        return segments.stream()
                .filter(segment ->
                    segment.getText()
                    .toLowerCase()
                    .contains(
                        query.toLowerCase()
                    )
                )
                .findFirst()
                .orElseThrow(() ->
                    new RuntimeException(
                        "Topic not found"));
    }
}
