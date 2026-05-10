package com.aiapp.backend.service;

import org.springframework.stereotype.Service;

import com.aiapp.backend.entity.ExtractedContent;
import com.aiapp.backend.entity.UploadedFile;
import com.aiapp.backend.repository.ExtractedContentRepository;
import com.aiapp.backend.repository.UploadedFileRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SummaryService {

    private final UploadedFileRepository
            uploadedFileRepository;

    private final ExtractedContentRepository
            extractedContentRepository;

    private final LlmService llmService;

    public String generateSummary(Long fileId) {

        UploadedFile uploadedFile =
                uploadedFileRepository
                .findById(fileId)
                .orElseThrow(() ->
                    new RuntimeException(
                        "File not found"));

        ExtractedContent extractedContent =
                extractedContentRepository
                .findByUploadedFile(uploadedFile)
                .orElseThrow(() ->
                    new RuntimeException(
                        "Extracted content not found"));

        String extractedText =
                extractedContent
                .getExtractedText();

        if (extractedText.length() > 12000) {
            extractedText =
                extractedText.substring(0, 12000);
        }

        String summary =
                llmService
                .generateSummary(extractedText);

        extractedContent.setSummary(summary);

        extractedContentRepository
                .save(extractedContent);

        return summary;
    }
}
