package com.example.demo.mensagem.mapper;

import java.util.List;
import java.util.Optional;

import com.example.demo.mensagem.Message;
import com.example.demo.mensagem.dto.CreateMessageRequestDto;
import com.example.demo.mensagem.dto.ResponseMessageDto;
import com.example.demo.usuario.IUserRepository;
import com.example.demo.usuario.User;
import lombok.AllArgsConstructor;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@AllArgsConstructor
@Mapper( componentModel = "spring")
public abstract class MessageMapper {


    private final IUserRepository userRepository;

    @Mapping( target = "id", ignore = true)
    @Mapping(target = )
	public abstract Message toDocument (CreateMessageRequestDto createMessageRequestDto);

	public abstract ResponseMessageDto toResponse (Message message);
	
	public abstract List<ResponseMessageDto> toResponseList (List<Message> messageList);

	@AfterMapping
	protected void completeMessageResponse(Message message, @MappingTarget ResponseMessageDto response) {
		Optional<User> senderOpt = userRepository.findById(message.getSenderId());
        senderOpt.ifPresent(sender -> response.setSenderName(sender.getName()));
	
	}
}
