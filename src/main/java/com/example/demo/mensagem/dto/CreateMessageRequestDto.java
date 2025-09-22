package com.example.demo.mensagem.dto;

import com.example.demo.mensagem.TypeMessage;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Data;


@Data
public class CreateMessageRequestDto {
		
	@NotBlank(message = "O ID do chat é obrigatorio")
	private String idChat;
	
	@NotNull(message ="O tipo de mensagem é obrigatorio")
	private TypeMessage typeMessage;
	
	@NotBlank(message ="Não pode ser nulo")
	private String content;
}
