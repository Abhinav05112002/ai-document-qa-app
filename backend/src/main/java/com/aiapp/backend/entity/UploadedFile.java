package com.aiapp.backend.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "uploaded_files")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UploadedFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String originalFileName;

    private String storedFileName;

    private String fileType;

    private String filePath;
    
    private String fileUrl;

    private Long fileSize;

    private LocalDateTime uploadedAt;
}