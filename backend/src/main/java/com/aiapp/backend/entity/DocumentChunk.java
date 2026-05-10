package com.aiapp.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "document_chunks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DocumentChunk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "file_id")
    private UploadedFile uploadedFile;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String chunkText;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String embedding;
}
