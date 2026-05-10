package com.aiapp.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aiapp.backend.entity.Transcript;
import com.aiapp.backend.entity.TranscriptSegment;

public interface TranscriptSegmentRepository
        extends JpaRepository<TranscriptSegment, Long> {

    List<TranscriptSegment>
        findByTranscript(Transcript transcript);
}