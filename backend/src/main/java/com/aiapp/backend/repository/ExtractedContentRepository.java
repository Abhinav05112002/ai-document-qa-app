package com.aiapp.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aiapp.backend.entity.ExtractedContent;
import java.util.Optional;
import com.aiapp.backend.entity.UploadedFile;

public interface ExtractedContentRepository
        extends JpaRepository<ExtractedContent, Long> {
	
	Optional<ExtractedContent>
    findByUploadedFile(UploadedFile uploadedFile);
}


