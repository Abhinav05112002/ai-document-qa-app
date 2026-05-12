package com.aiapp.backend.service;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.aiapp.backend.dto.FileUploadResponse;
import com.aiapp.backend.entity.UploadedFile;
import com.aiapp.backend.repository.UploadedFileRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileService {

    private final UploadedFileRepository uploadedFileRepository;
    
    private final PdfService pdfService;
    
    private final TranscriptionService transcriptionService;
    
    private final RagService ragService;

    @Value("${app.base-url}")
    private String baseUrl;

    @Value("${file.upload-dir}")
    private String uploadDir;

    public FileUploadResponse uploadFile(
            MultipartFile file) throws IOException {

        validateFile(file);

        String originalFileName =
                file.getOriginalFilename();

        String extension =
                getFileExtension(originalFileName);

        String uniqueFileName =
                UUID.randomUUID() + "." + extension;

        Path uploadPath =
                Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        Path filePath =
                uploadPath.resolve(uniqueFileName);

        Files.copy(
                file.getInputStream(),
                filePath,
                StandardCopyOption.REPLACE_EXISTING
        );

        UploadedFile uploadedFile =
                UploadedFile.builder()
                .originalFileName(originalFileName)
                .storedFileName(uniqueFileName)
                .fileType(file.getContentType())
                .filePath(filePath.toString())
                .fileSize(file.getSize())
                .uploadedAt(LocalDateTime.now())
                .build();

        UploadedFile savedFile =
                uploadedFileRepository.save(uploadedFile);
        
        savedFile.setFileUrl(
                baseUrl + "/uploads/"
                + savedFile.getStoredFileName()
        );
        
        uploadedFileRepository.save(savedFile);

        if (file.getContentType()
                .equals("application/pdf")) {

        	pdfService.extractTextFromPdf(savedFile);

        	ragService.processDocument(savedFile);
        }

        else if (
            file.getContentType()
                .startsWith("audio/")
            ||
            file.getContentType()
                .startsWith("video/")
        ) {

            try {
				transcriptionService
				    .processMediaFile(savedFile);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }

        return FileUploadResponse.builder()
                .id(savedFile.getId())
                .fileName(originalFileName)
                .fileType(file.getContentType())
                .fileSize(file.getSize())
                .fileUrl(savedFile.getFileUrl())
                .message("File uploaded successfully")
                .build();
    }
    
    private void validateFile(MultipartFile file) {

        if (file.isEmpty()) {
            throw new RuntimeException(
                    "File is empty");
        }

        String contentType =
                file.getContentType();

        if (contentType == null) {
            throw new RuntimeException(
                    "Invalid file type");
        }

        boolean supported =
                contentType.equals("application/pdf")
                || contentType.startsWith("audio/")
                || contentType.startsWith("video/");

        if (!supported) {
            throw new RuntimeException(
                    "Only PDF, audio, and video files are allowed");
        }
    }

    private String getFileExtension(
            String fileName) {

        return fileName.substring(
                fileName.lastIndexOf(".") + 1);
    }
}
