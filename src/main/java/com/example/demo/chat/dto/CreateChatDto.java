package com.example.demo.chat.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;



public record CreateChatDto (
        @NotBlank(message = "Para criação de um novo chat o e-mail é obrigatorio")
        @Email(message = "Formato de e-mail invalido")
        String receiverEmail

){}



