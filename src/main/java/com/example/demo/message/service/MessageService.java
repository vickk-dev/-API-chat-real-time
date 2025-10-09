package com.example.demo.message.service;

import com.example.demo.message.IMessageRepository;
import com.example.demo.message.Message;
import com.example.demo.message.dto.CreateMessageRequestDto;
import com.example.demo.message.dto.ResponseMessageDto;
import com.example.demo.message.mapper.MessageMapper;
import com.example.demo.user.IUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class MessageService {

  private final MessageMapper messageMapper;
  private final IMessageRepository messageRepository;
  private final IUserRepository userRepository;
  private final KafkaTemplate<String, ResponseMessageDto> kafkaTemplate;
  private static final String KAFKA_TOPIC = "chat-messages";

     public ResponseMessageDto addMessage(CreateMessageRequestDto createMessageRequestDto, String senderId){
         Message newMessage = messageMapper.toDocument(createMessageRequestDto);


         newMessage.setTimestamp(Instant.now());

         ResponseMessageDto responseMessageDto = messageMapper.toResponse(messageRepository.save(newMessage));
         kafkaTemplate.send(KAFKA_TOPIC, responseMessageDto.getIdChat(), responseMessageDto);

         return responseMessageDto;

     }


     public ResponseMessageDto findMessageBySenderId(String senderId){

         return (ResponseMessageDto) Optional.of(messageRepository.findBySenderId(senderId))
                 .filter(messages -> !messages.isEmpty())
                 .map(messages ->messages.stream().map(messageMapper::toResponse)
                         .collect(Collectors.toList())).orElseThrow(() -> new NoSuchElementException(
                                 "Não foi encontrado nenhuma mensagem do remetente" + senderId));


       }

     }



