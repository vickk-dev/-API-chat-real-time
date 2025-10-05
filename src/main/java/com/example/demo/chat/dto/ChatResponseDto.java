package com.example.demo.chat.dto;

import java.util.List;


import com.example.demo.usuario.dto.ResponseUserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChatResponseDto {
	private String id;
	private String displayName;
	private List<ResponseUserDto> participants;
	
}
