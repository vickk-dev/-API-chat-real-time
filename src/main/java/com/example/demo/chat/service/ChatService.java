package com.example.demo.chat.service;

import com.example.demo.chat.IChatRepository;
import com.example.demo.chat.dto.ChatResponseDto;
import com.example.demo.chat.mapper.ChatMapper;
import com.example.demo.message.IMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {


    private final IChatRepository chatRepository;
    private final IMessageRepository messageRepository;
    private final ChatMapper chatMapper;

    public ChatResponseDto createChat(CreateCh)
}
