package com.aiapp.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aiapp.backend.entity.Transcript;
import com.aiapp.backend.entity.UploadedFile;

public interface TranscriptRepository
        extends JpaRepository<Transcript, Long> {
	
	Optional<Transcript>findByUploadedFile(UploadedFile uploadedFile);
}
