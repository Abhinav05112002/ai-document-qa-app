package com.aiapp.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "transcripts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transcript {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "file_id")
    private UploadedFile uploadedFile;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String transcriptText;
}
