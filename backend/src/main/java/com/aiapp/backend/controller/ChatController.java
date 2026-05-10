package com.aiapp.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.aiapp.backend.dto.ChatRequest;
import com.aiapp.backend.dto.ChatResponse;
import com.aiapp.backend.service.ChatService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService
            chatService;

    @PostMapping
    public ResponseEntity<ChatResponse>
        chat(
            @RequestBody
            ChatRequest request) {

        return ResponseEntity.ok(
                chatService.askQuestion(
                    request.getFileId(),
                    request.getQuestion()
                )
        );
    }
}