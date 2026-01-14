package com.example.demo.message.controller;

import com.example.demo.message.dto.ResponseMessageDto;
import com.example.demo.message.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    @GetMapping("/chat/{chatId}")
    public ResponseEntity<List<ResponseMessageDto>> getChatHistory(@PathVariable("chatId") String chatId) {
        List<ResponseMessageDto> history = messageService.getHistoryForChat(chatId);
        return ResponseEntity.ok(history);


    }



}