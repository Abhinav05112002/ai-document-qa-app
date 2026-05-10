package com.aiapp.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aiapp.backend.dto.ChatResponse;
import com.aiapp.backend.entity.DocumentChunk;
import com.aiapp.backend.entity.UploadedFile;
import com.aiapp.backend.repository.DocumentChunkRepository;
import com.aiapp.backend.repository.UploadedFileRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final UploadedFileRepository
            uploadedFileRepository;

    private final DocumentChunkRepository
            documentChunkRepository;

    private final VectorSearchService
            vectorSearchService;

    private final LlmService
            llmService;

    public ChatResponse askQuestion(
            Long fileId,
            String question) {

        UploadedFile uploadedFile =
                uploadedFileRepository
                .findById(fileId)
                .orElseThrow(() ->
                    new RuntimeException(
                        "File not found"));

        List<DocumentChunk> chunks =
                documentChunkRepository
                .findByUploadedFile(uploadedFile);

        DocumentChunk relevantChunk =
                vectorSearchService
                .findMostRelevantChunk(
                    question,
                    chunks
                );

        String prompt = buildPrompt(
                relevantChunk.getChunkText(),
                question
        );

        String answer =
                llmService
                .generateAnswer(prompt);

        return ChatResponse.builder()
                .answer(answer)
                .sourceChunk(
                    relevantChunk.getChunkText()
                )
                .build();
    }

    private String buildPrompt(
            String context,
            String question) {

        return """
                You are an AI assistant.

                Answer ONLY using the provided context.

                If the answer is not present,
                say:
                "Answer not found in document."

                CONTEXT:
                %s

                QUESTION:
                %s
                """
                .formatted(context, question);
    }
}
