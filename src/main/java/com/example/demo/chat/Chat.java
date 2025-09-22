package com.example.demo.chat;

 
import java.time.Instant;

import java.util.ArrayList;

import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;




import lombok.Data;

@Data
@Document(collation ="chat")
public class Chat {
	
	@Id
	private String id;
	
	private String displayName;

	private List<String> participants = new ArrayList<>();

	private TypoChat typoChat;
	
	private String lastMessage;
	
	@CreatedDate
	private Instant createData;
	
	@LastModifiedDate
	private Instant updatedAt;
	
	
}
