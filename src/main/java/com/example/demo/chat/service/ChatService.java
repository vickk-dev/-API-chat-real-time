package com.example.demo.chat.service;

import com.example.demo.chat.Chat;
import com.example.demo.chat.IChatRepository;
import com.example.demo.chat.dto.ChatResponseDto;
import com.example.demo.chat.dto.CreateChatDto;
import com.example.demo.chat.mapper.ChatMapper;
import com.example.demo.message.IMessageRepository;
import com.example.demo.user.IUserRepository;
import com.example.demo.user.User;
import com.example.demo.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatService {


    private final IChatRepository chatRepository;
    private final IMessageRepository messageRepository;
    private final ChatMapper chatMapper;
    private final UserMapper userMapper;
    private final IUserRepository userRepository;

    public List<Chat> createOrGetChat(CreateChatDto createChatDto) {

        User receiver = userRepository.findByEmail(createChatDto.getReceiverEmail())
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
        
        
        List<String> participants = new ArrayList<>();



        participants.add(createChatDto.getSenderId());
        participants.add(receiver.getId());


        Collections.sort(participants);
        
        return chatRepository.findByParticipants(participants);
    }

}
