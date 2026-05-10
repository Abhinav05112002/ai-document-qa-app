package com.aiapp.backend.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.aiapp.backend.dto.FileUploadResponse;
import com.aiapp.backend.service.FileService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<FileUploadResponse>
            uploadFile(
                @RequestParam("file")
                MultipartFile file)
                throws IOException {

        FileUploadResponse response =
                fileService.uploadFile(file);

        return ResponseEntity.ok(response);
    }
}