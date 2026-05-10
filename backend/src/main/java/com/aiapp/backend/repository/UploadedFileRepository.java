package com.aiapp.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aiapp.backend.entity.UploadedFile;

public interface UploadedFileRepository
        extends JpaRepository<UploadedFile, Long> {
}