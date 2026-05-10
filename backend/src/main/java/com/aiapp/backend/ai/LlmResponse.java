package com.aiapp.backend.ai;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LlmResponse {

    private List<Choice> choices;

    @Getter
    @Setter
    public static class Choice {

        private Message message;
    }

    @Getter
    @Setter
    public static class Message {

        private String role;

        private String content;
    }
}
