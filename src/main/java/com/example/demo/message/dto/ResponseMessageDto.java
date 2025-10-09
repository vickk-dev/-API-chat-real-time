package com.example.demo.message.dto;

import com.example.demo.message.TypeMessage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
public class ResponseMessageDto {

	private String id;
	private String content;
	private TypeMessage typeMessage;
	private String senderName;
	private String timestamp;
	private String senderId;
	private String idChat;

	
	

}
