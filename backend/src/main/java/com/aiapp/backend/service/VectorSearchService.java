package com.aiapp.backend.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.aiapp.backend.entity.DocumentChunk;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VectorSearchService {

    private final EmbeddingService
            embeddingService;

    public DocumentChunk findMostRelevantChunk(
            String query,
            List<DocumentChunk> chunks) {

        float[] queryEmbedding =
                embeddingService
                .generateEmbedding(query);

        return chunks.stream()
                .max(
                    Comparator.comparingDouble(
                        chunk ->
                            cosineSimilarity(
                                queryEmbedding,
                                embeddingService
                                .stringToEmbedding(
                                    chunk.getEmbedding()
                                )
                            )
                    )
                )
                .orElseThrow(() ->
                    new RuntimeException(
                        "No relevant chunk found"));
    }

    private double cosineSimilarity(
            float[] a,
            float[] b) {

        double dotProduct = 0.0;
        double normA = 0.0;
        double normB = 0.0;

        for (int i = 0; i < a.length; i++) {

            dotProduct += a[i] * b[i];

            normA += Math.pow(a[i], 2);

            normB += Math.pow(b[i], 2);
        }

        return dotProduct /
                (
                    Math.sqrt(normA)
                    *
                    Math.sqrt(normB)
                );
    }
}
