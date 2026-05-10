package com.aiapp.backend.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "transcript_segments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TranscriptSegment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "transcript_id")
    private Transcript transcript;

    @JsonProperty("startTime")
    private Double startTime;

    @JsonProperty("endTime")
    private Double endTime;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String text;
}