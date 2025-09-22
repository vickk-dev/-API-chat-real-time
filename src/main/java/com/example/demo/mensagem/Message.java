package com.example.demo.mensagem;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;




@Data
@Document(collation ="mensagem")
public class Message {

	@Id
	private String id;

	private String content;
	
	private Instant timestamp;
		
	private TypeMessage typeMessage;
	
	private String senderId;

	private String idChat;
}
