package com.example.demo.mensagem;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface IMessageRepository extends MongoRepository<Message, String>{

    Optional<Message> findByContent(String content);
	
	Optional <Message>findByTypeMessage(TypeMessage typoMessage);
	
	
	
}
