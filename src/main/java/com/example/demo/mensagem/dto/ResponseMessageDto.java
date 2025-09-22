package com.example.demo.mensagem.dto;

import com.example.demo.mensagem.TypeMessage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class ResponseMessageDto {

	private String id;
	private String content;
	private TypeMessage tipo;
	private String senderName;
	private String timestamp;
	private String senderId;
	
	
	

}
