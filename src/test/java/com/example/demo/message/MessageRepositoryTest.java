package com.example.demo.message;


import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.Instant;
import java.util.List;
@Testcontainers
@DataMongoTest
class MessageRepositoryTest {


    @Container
    @ServiceConnection
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:6.0");

    @Autowired
    private IMessageRepository messageRepository;

    @Test
    @DisplayName("Deve salvar uma mensagem e encontrar pelo SenderId")
    void shouldSaveAndFindMessage() {

        String senderId = "user123";
        Message message = new Message();
        message.setContent("Ola");
        message.setSenderId(senderId);
        message.setTypeMessage(TypeMessage.TEXT);
        message.setTimestamp(Instant.now());
        message.setIdChat("chat1");


        messageRepository.save(message);
        List<Message> foundMessages = messageRepository.findBySenderId(senderId);


        Assertions.assertFalse(foundMessages.isEmpty());
        Assertions.assertEquals("Ola", foundMessages.get(0).getContent());
        Assertions.assertEquals(senderId, foundMessages.get(0).getSenderId());
    }

    @Test
    @DisplayName("Deve retornar vazio quando SenderId não existir")
    void shouldReturnEmptyWhenSenderIdNotFound() {
        List<Message> foundMessages = messageRepository.findBySenderId("usuario_inexistente");
        Assertions.assertTrue(foundMessages.isEmpty());
    }
}
