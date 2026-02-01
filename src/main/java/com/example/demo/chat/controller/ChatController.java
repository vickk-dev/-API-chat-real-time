package com.example.demo.chat.controller;

import com.example.demo.chat.dto.ChatResponseDto;
import com.example.demo.chat.dto.ChatSummaryDto;
import com.example.demo.chat.dto.CreateChatDto;
import com.example.demo.chat.service.ChatService;
import com.example.demo.config.GlobalExeptionHandler;
import com.example.demo.message.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor
@RestController
@Tag(name = "Gerenciamento de Chats", description = "Endpoints para criar, buscar e deletar conversas")
@RequestMapping("/api/chat")
public class ChatController {

    private final MessageService messageService;
    private final ChatService chatService;
    private final GlobalExeptionHandler globalExeptionHandler;


    @PostMapping
    @Operation(summary = "Iniciar nova conversa", description = "Cria uma sala de chat entre o usuário logado e o email informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Chat criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação(ex: valores nulos"),
            @ApiResponse(responseCode = "500", description = "Erro interno")

    })
    public ResponseEntity<ChatResponseDto> CreateChat (@Valid @RequestBody CreateChatDto  createChatDto) {

        ChatResponseDto newChat = chatService.createChat(createChatDto);
        return ResponseEntity.status(201).body(newChat);



    }





    @GetMapping("/search")
    @Operation(summary = "Busca o chat pelo e-mail do destinatario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Chat retornado com sucesso "),
            @ApiResponse(responseCode = "400", description = "Erro de validação(ex: valores nulos"),
            @ApiResponse(responseCode = "500", description = "Erro interno")
    })
    public ResponseEntity<ChatResponseDto> getChatByEmail(@RequestParam("email") String email) {

        ChatResponseDto chat = chatService.getChatByReceiverEmail(email);
        return ResponseEntity.ok(chat);

    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta o chat pelo Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deletado com sucesso (Sem conteúdo)"),
            @ApiResponse(responseCode = "404", description = "Chat não encontrado")
    })
    public ResponseEntity<Void> deleteChat(@PathVariable String id) {
        chatService.deleteChat(id);
        return ResponseEntity.noContent().build();
    }
}