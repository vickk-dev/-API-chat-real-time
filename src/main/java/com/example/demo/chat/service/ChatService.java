package com.example.demo.chat.service;

import com.example.demo.chat.Chat;
import com.example.demo.chat.IChatRepository;
import com.example.demo.chat.dto.ChatResponseDto;
import com.example.demo.chat.dto.CreateChatDto;
import com.example.demo.chat.mapper.ChatMapper;
import com.example.demo.message.IMessageRepository;
import com.example.demo.security.UserContext;
import com.example.demo.user.IUserRepository;
import com.example.demo.user.User;
import com.example.demo.user.mapper.UserMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.example.demo.chat.ChatType.CHAT_PRIVADO;

@Service
@RequiredArgsConstructor
public class ChatService {


    private final IChatRepository chatRepository;
    private final IMessageRepository messageRepository;
    private final ChatMapper chatMapper;
    private final UserMapper userMapper;
    private final IUserRepository userRepository;
    private final UserContext userContext;


    public Chat createChat(CreateChatDto createChatDto) {


        User receiver = userRepository.findByEmail(createChatDto.receiverEmail())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado: " + createChatDto.receiverEmail()));

        User currentUser = userContext.GetCurrentUser();


        List<String> participants = new ArrayList<>();
        participants.add(currentUser.getId());
        participants.add(receiver.getId());
        Collections.sort(participants);

        Optional<Chat> existingChat = chatRepository.findByParticipants(participants);

        if (existingChat.isPresent()) {
            return existingChat.get();
        }


        Chat newChat = new Chat();
        newChat.setParticipants(participants);
        newChat.setChatType(CHAT_PRIVADO); // por enquanto fica setado o chat privado.
        newChat.setCreateAt(Instant.now());
        newChat.setDisplayName(receiver.getName());
        return chatRepository.save(newChat);
    }
   public ChatResponseDto getChatByReceiverEmail(String receiverEmail) {
        User receiver = userRepository.findByEmail(receiverEmail)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com o email: " + receiverEmail));


            User currentUser = userContext.GetCurrentUser();

            Chat chat = (Chat) chatRepository.findChatByUsers(currentUser, receiver)
                .orElseThrow(() -> new EntityNotFoundException("Não existe nenhum chat iniciado com esse usuário."));

        return chatMapper.toResponse(chat);
    }

    public ChatResponseDto getChatById(String id){
        Optional<Chat> chat = Optional.of(chatRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Chat com o Id: " + id + " não encontrado")));

        return chatMapper.toResponse(chat.get());

    }
}
