package com.example.demo.infrastructure.messaging;

import com.example.demo.message.dto.ResponseMessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.resource.ResourceTransformer;

import java.sql.SQLOutput;

@Service
@RequiredArgsConstructor
public class KafkaConsumerService {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ResourceTransformer resourceTransformer;

    @KafkaListener(topics = "chat-messages", groupId = "chat-grup")
    public void consummerMessage(ResponseMessageDto responseMessageDto){

        String websocketDestination = "/topic/chat/" + responseMessageDto.getIdChat();
        simpMessagingTemplate.convertAndSend(websocketDestination, responseMessageDto);
    }
}
