package com.example.demo.chat.dto;

import java.time.LocalDateTime;
import java.util.List;


import com.example.demo.user.dto.ResponseUserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChatResponseDto {
	private String id;
	private String displayName;
	private List<ResponseUserDto> participants;
	private String receiverEmail;
	private LocalDateTime createdAt;

}
