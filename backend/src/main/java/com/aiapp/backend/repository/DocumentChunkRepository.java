package com.aiapp.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aiapp.backend.entity.DocumentChunk;
import com.aiapp.backend.entity.UploadedFile;

public interface DocumentChunkRepository
        extends JpaRepository<DocumentChunk, Long> {

    List<DocumentChunk>
        findByUploadedFile(
            UploadedFile uploadedFile
        );
}
