package com.example.demo.message;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface IMessageRepository extends MongoRepository<Message, String> {

    Optional<Message> findByContent(String content);

    Optional<Message> findByTypeMessage(TypeMessage typoMessage);

    List<Message> findBySenderId(String senderId);

    List<Message> findByTimestamp(Instant timestamp);

    Optional<Message> findByIdChat(String idChat);

    List<Message> findBySenderIdAndTimestamp(String chatId);

}
