package com.aiapp.backend.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileUploadResponse {

    private Long id;

    private String fileName;

    private String fileType;

    private Long fileSize;

    private String message;
    
    private String fileUrl;
}
