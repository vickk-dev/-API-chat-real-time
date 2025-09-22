package com.example.demo.chat.dto;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChatSummaryDto {

	private String id;
	private String displayNome;
	private String messagePreview;
	private Instant TimestampLastMessage;
	
}
