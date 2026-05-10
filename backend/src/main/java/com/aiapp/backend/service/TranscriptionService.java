package com.aiapp.backend.service;

import java.io.File;

import org.springframework.stereotype.Service;

import com.aiapp.backend.entity.Transcript;
import com.aiapp.backend.entity.UploadedFile;
import com.aiapp.backend.repository.TranscriptRepository;
import com.aiapp.backend.repository.TranscriptSegmentRepository;
import com.aiapp.backend.ai.WhisperResponse;
import com.aiapp.backend.entity.TranscriptSegment;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TranscriptionService {

    private final WhisperService whisperService;

    private final FfmpegService ffmpegService;

    private final TranscriptRepository
            transcriptRepository;
    
    private final TranscriptSegmentRepository
    transcriptSegmentRepository;

    public void processMediaFile(
            UploadedFile uploadedFile)
            throws Exception {

        String contentType =
                uploadedFile.getFileType();

        File audioFile;

        if (contentType.startsWith("video/")) {

            audioFile =
                ffmpegService
                .extractAudioFromVideo(
                    uploadedFile.getFilePath());

        } else {

            audioFile =
                new File(
                    uploadedFile.getFilePath());
        }

        WhisperResponse response =
                whisperService
                .transcribeAudio(audioFile);

        Transcript transcriptEntity =
                Transcript.builder()
                .uploadedFile(uploadedFile)
                .transcriptText(response.getText())
                .build();

        Transcript savedTranscript =
                transcriptRepository
                .save(transcriptEntity);
        
        for (WhisperResponse.Segment segment
                : response.getSegments()) {

            TranscriptSegment transcriptSegment =
                    TranscriptSegment.builder()
                    .transcript(savedTranscript)
                    .startTime(segment.getStart())
                    .endTime(segment.getEnd())
                    .text(segment.getText())
                    .build();

            transcriptSegmentRepository
                    .save(transcriptSegment);
        }
    }
}