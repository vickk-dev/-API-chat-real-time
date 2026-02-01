package com.example.demo.message.controller;

import com.example.demo.message.dto.CreateMessageRequestDto;
import com.example.demo.message.dto.ResponseMessageDto;
import com.example.demo.message.service.MessageService;
import com.example.demo.config.security.UserContext;
import com.example.demo.user.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
@Tag(name = "Messagens", description = "Endpoints que gerenciam o envio de messagem em tempo real")
public class MessageController {
    private final MessageService messageService;
    private final UserContext userContext;

    @PostMapping
    @Operation(summary = "Enviar mensagens", description = "Recebe uma nova mensagem, salva no banco e despacha para o Kafka/WebSocket.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Messagem enviada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação(ex: valores nulos"),
            @ApiResponse(responseCode = "404", description = "Chat não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno")

    })
    public ResponseEntity<ResponseMessageDto> sendMessage(@Valid @RequestBody CreateMessageRequestDto createMessageRequestDto) {

        User currentUser = userContext.GetCurrentUser();

        ResponseMessageDto sentMessage = messageService.addMessage(createMessageRequestDto, currentUser.getId());
        return ResponseEntity.status(201).body(sentMessage);

    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar mensagem por ID", description = "Recupera os detalhes de uma única mensagem")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Mensagem encontrada"),
            @ApiResponse(responseCode = "404", description = "Mensagem não existe")
    })
    public ResponseEntity<ResponseMessageDto> getMessage(@PathVariable String id) {
        ResponseMessageDto message = messageService.getMessageById(id);
        return ResponseEntity.ok(message);
    }
}








