package com.example.demo.chat.mapper;

import java.util.List;

import com.example.demo.usuario.IUserRepository;
import com.example.demo.usuario.User;
import com.example.demo.usuario.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.mapstruct.*;

import com.example.demo.chat.Chat;
import com.example.demo.chat.dto.ChatResponseDto;
import com.example.demo.chat.dto.ChatSummaryDto;



@AllArgsConstructor
@Mapper( componentModel = "spring", uses = {UserMapper.class})
public abstract class ChatMapper {




	private final IUserRepository userRepository;

	private final UserMapper userMapper;
	

	public abstract ChatSummaryDto toSummary  (Chat chat);
	
	public abstract List<ChatSummaryDto> toSumaryList(List<Chat>chat);
	
	


	public abstract ChatResponseDto toResponse (Chat chat);


	public abstract List<ChatResponseDto> toResponseDtoList (List<Chat> chat);
	
	@AfterMapping
	protected void completeChatResponse(Chat chat, @MappingTarget ChatResponseDto chatResponseDto) {
		
		List<User> participants = userRepository.findAllById(chat.getParticipants());

		chatResponseDto.setParticipants(userMapper.toResponseList(participants));
	}
	
	
	
}
