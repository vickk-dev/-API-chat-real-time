package com.example.demo.chat;

import com.example.demo.chat.dto.ChatResponseDto;
import com.example.demo.chat.dto.CreateChatDto;
import com.example.demo.chat.mapper.ChatMapper;
import com.example.demo.chat.service.ChatService;
import com.example.demo.security.UserContext;
import com.example.demo.user.IUserRepository;
import com.example.demo.user.User;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.mongodb.assertions.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class ChatServiceTest {

    @Mock
    private IChatRepository chatRepository;
    @Mock
    private  ChatMapper chatMapper;
    @Mock
    private  IUserRepository userRepository;
    @Mock
    private UserContext userContext;
    @InjectMocks
    private ChatService chatService;



    @Test
    @DisplayName("Deve Criar Chat Com Participantes Corretamente")
    void mustCreateChatWithParticipantsCorrectly() {

        String emailReceiver = "123@gmail.com";

        User loggedUser = new User();
        loggedUser.setId("User1");
        loggedUser.setEmail("163@gmail.com");

        User receiverUser = new User();
        receiverUser.setId("User2");

        when(userRepository.findByEmail(emailReceiver)).thenReturn(Optional.of(receiverUser));

        when(userContext.GetCurrentUser()).thenReturn(loggedUser);

        when(chatRepository.findByParticipants(any())).thenReturn(Optional.empty());

        when(chatRepository.save(any(Chat.class))).thenAnswer(i -> i.getArgument(0));

        CreateChatDto createChatDto = new CreateChatDto(emailReceiver);

         chatService.createChat(createChatDto);

        ArgumentCaptor<Chat> captor = ArgumentCaptor.forClass(Chat.class);

        verify(chatRepository).save(captor.capture());

        Chat saveChat = captor.getValue();

        assertNotNull(saveChat);
        assertEquals(2, saveChat.getParticipants().size());
        assertTrue(saveChat.getParticipants().contains(loggedUser.getId()));
        assertTrue(saveChat.getParticipants().contains(receiverUser.getId()));
    }


    @Test
    @DisplayName("Deve Retornar Chat Response Quando Chat Existir")
    void shouldReturnChatResponseWhenChatExists(){


        String emailReceiver = "123@gmail.com";
        User loggedUser = new User();
        loggedUser.setId("User1");
        loggedUser.setEmail("123@gmail.com");

        User receiverUser = new User();
        receiverUser.setId("User2");
        receiverUser.setEmail(emailReceiver);
        Chat existentChat = new Chat();
        ChatResponseDto response = new ChatResponseDto();

        when(userRepository.findByEmail(emailReceiver)).thenReturn(Optional.of(receiverUser));
        when(userContext.GetCurrentUser()).thenReturn(loggedUser);
        when(chatRepository.findByParticipants(any())).thenReturn(Optional.of(existentChat));
        when(chatMapper.toResponse(existentChat)).thenReturn(response);

        ChatResponseDto result = chatService.getChatByReceiverEmail(emailReceiver);

        assertNotNull(result);
        assertEquals(response,result);

        verify(chatMapper,times(1)).toResponse(existentChat);

    }


    @Test
    @DisplayName("Deve Lancar Uma Execao Quando Usuario De Destino Nao Existir")
    void MustLaunchAnExceptionWhenYourTargetUserDoesNotExist(){
        String emailNonExists = "inexistente@outlook.com";
        when(userRepository.findByEmail(emailNonExists)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(
                EntityNotFoundException.class,()-> {chatService.getChatByReceiverEmail(emailNonExists);
                });
        assertEquals("Usuário não encontrado com o email: " + emailNonExists, exception.getMessage());

        verify(chatRepository, never()).findByParticipants(any());

    }

    @Test
    @DisplayName("Deve Lancar Uma Execao Quando Chat Nao Existir")
    void mustLaunchAnExecutionWhenChatDoesNotExist(){

        String emailReceiver = "123@gmail.com";
        User loggedUser = new User();
        loggedUser.setId("User1");
        loggedUser.setEmail("123@gmail.com");

        User receiverUser = new User();
        receiverUser.setId("User2");
        when(userRepository.findByEmail(emailReceiver)).thenReturn(Optional.of(receiverUser));
        when(userContext.GetCurrentUser()).thenReturn(loggedUser);

        when(chatRepository.findByParticipants(any())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            chatService.getChatByReceiverEmail(emailReceiver);
        });
        verify(chatRepository, times(1)).findByParticipants(any());

    }

}
