package com.example.demo.mensagem.service;

import com.example.demo.chat.Chat;
import com.example.demo.chat.mapper.ChatMapper;
import com.example.demo.mensagem.IMessageRepository;
import com.example.demo.mensagem.Message;
import com.example.demo.mensagem.dto.CreateMessageRequestDto;
import com.example.demo.mensagem.dto.ResponseMessageDto;
import com.example.demo.mensagem.mapper.MessageMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@AllArgsConstructor
public class MessageService {

 final private MessageMapper messageMapper;

 final private ChatMapper chatMapper;
 final private IMessageRepository messageRepository;


     public ResponseMessageDto addMessage(CreateMessageRequestDto createMessageRequestDto, String senderId){
         Message newMesssage = messageMapper.toDocument(createMessageRequestDto);

         newMesssage.setSenderId(senderId);
         newMesssage.setTimestamp(Instant.now());

         return messageMapper.toResponse(messageRepository.save(newMesssage));

     }


}
