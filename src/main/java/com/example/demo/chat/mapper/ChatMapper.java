package com.example.demo.chat.mapper;


import com.example.demo.chat.Chat;
import com.example.demo.chat.dto.ChatResponseDto;
import com.example.demo.chat.dto.ChatSummaryDto;
import com.example.demo.user.IUserRepository;
import com.example.demo.user.User;
import com.example.demo.user.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@AllArgsConstructor
@Mapper( componentModel = "spring", uses = {UserMapper.class})
public abstract class ChatMapper {

	private final IUserRepository userRepository;
	private final UserMapper userMapper;

    @Mapping(target = "displayNome", ignore = true)
    @Mapping(target = "messagePreview", ignore = true)
    @Mapping(target = "timestampLastMessage", ignore = true)
	public abstract ChatSummaryDto toSummary  (Chat chat);
	
	public abstract List<ChatSummaryDto> toSumaryList(List<Chat>chat);



    @Mapping(target = "participants", ignore = true)
	public abstract ChatResponseDto toResponse (Chat chat);


	public abstract List<ChatResponseDto> toResponseDtoList (List<Chat> chat);
	
	@AfterMapping
	protected void completeChatResponse(Chat chat, @MappingTarget ChatResponseDto chatResponseDto) {
		
		List<User> participants = userRepository.findAllById(chat.getParticipants());

		chatResponseDto.setParticipants(userMapper.toResponseList(participants));
	}
	
	
	
}
