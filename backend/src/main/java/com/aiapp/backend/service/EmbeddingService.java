package com.aiapp.backend.service;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class EmbeddingService {

    /*
     * TEMPORARY MOCK EMBEDDINGS
     * Later replace with HuggingFace model
     */

    public float[] generateEmbedding(
            String text) {

        Random random =
                new Random(text.hashCode());

        float[] embedding =
                new float[384];

        for (int i = 0; i < embedding.length; i++) {

            embedding[i] =
                    random.nextFloat();
        }

        return embedding;
    }

    public String embeddingToString(
            float[] embedding) {

        StringBuilder builder =
                new StringBuilder();

        for (float value : embedding) {

            builder.append(value)
                    .append(",");
        }

        return builder.toString();
    }

    public float[] stringToEmbedding(
            String embeddingString) {

        String[] values =
                embeddingString.split(",");

        float[] embedding =
                new float[values.length];

        for (int i = 0; i < values.length; i++) {

            embedding[i] =
                    Float.parseFloat(values[i]);
        }

        return embedding;
    }
}
