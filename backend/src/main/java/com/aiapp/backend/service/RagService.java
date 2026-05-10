package com.aiapp.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aiapp.backend.entity.DocumentChunk;
import com.aiapp.backend.entity.ExtractedContent;
import com.aiapp.backend.entity.UploadedFile;
import com.aiapp.backend.repository.DocumentChunkRepository;
import com.aiapp.backend.repository.ExtractedContentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RagService {

    private final ChunkingService
            chunkingService;

    private final EmbeddingService
            embeddingService;

    private final DocumentChunkRepository
            documentChunkRepository;

    private final ExtractedContentRepository
            extractedContentRepository;

    public void processDocument(
            UploadedFile uploadedFile) {

        ExtractedContent content =
                extractedContentRepository
                .findByUploadedFile(uploadedFile)
                .orElseThrow(() ->
                    new RuntimeException(
                        "Extracted content not found"));

        List<String> chunks =
                chunkingService.chunkText(
                    content.getExtractedText(),
                    500
                );

        for (String chunkText : chunks) {

            float[] embedding =
                    embeddingService
                    .generateEmbedding(chunkText);

            DocumentChunk chunk =
                    DocumentChunk.builder()
                    .uploadedFile(uploadedFile)
                    .chunkText(chunkText)
                    .embedding(
                        embeddingService
                        .embeddingToString(
                            embedding
                        )
                    )
                    .build();

            documentChunkRepository
                    .save(chunk);
        }
    }
}
