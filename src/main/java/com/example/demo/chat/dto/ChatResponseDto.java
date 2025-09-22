package com.example.demo.chat.dto;

import java.util.List;


import com.example.demo.usuario.dto.ResponseUserDto;
import lombok.Data;


@Data
public class ChatResponseDto {
	private String id;
	private String displayName;
	private List<ResponseUserDto> participants;
	
}
