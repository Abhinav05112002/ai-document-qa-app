package com.aiapp.backend.service;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;

@Service
public class FfmpegService {

    public File extractAudioFromVideo(
            String videoPath)
            throws IOException, InterruptedException {

        String outputAudio =
                videoPath + ".wav";

        ProcessBuilder processBuilder =
                new ProcessBuilder(
                    "ffmpeg",
                    "-i",
                    videoPath,
                    "-vn",
                    "-acodec",
                    "pcm_s16le",
                    "-ar",
                    "16000",
                    "-ac",
                    "1",
                    outputAudio
                );

        processBuilder.redirectErrorStream(true);

        Process process =
                processBuilder.start();

        int exitCode =
                process.waitFor();

        if (exitCode != 0) {
            throw new RuntimeException(
                    "FFmpeg failed");
        }

        return new File(outputAudio);
    }
}
