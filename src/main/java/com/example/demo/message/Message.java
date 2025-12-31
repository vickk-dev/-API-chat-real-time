package com.example.demo.message;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;




@Data
@Document(collection ="mensagem")
public class Message {

	@Id
	private String id;


	private String content;
	
	private Instant timestamp;
		
	private TypeMessage typeMessage;
	
	private String senderId;
	@Indexed
	private String idChat;
}
