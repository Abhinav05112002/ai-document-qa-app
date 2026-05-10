package com.aiapp.backend.ai;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WhisperResponse {

    private String text;

    private List<Segment> segments;

    @Getter
    @Setter
    public static class Segment {

        private Double start;

        private Double end;

        private String text;
    }
}
