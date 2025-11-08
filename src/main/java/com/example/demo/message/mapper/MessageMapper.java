package com.example.demo.message.mapper;

import com.example.demo.message.Message;
import com.example.demo.message.dto.CreateMessageRequestDto;
import com.example.demo.message.dto.ResponseMessageDto;
import com.example.demo.user.IUserRepository;
import com.example.demo.user.User;
import lombok.RequiredArgsConstructor;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;



@RequiredArgsConstructor
@Mapper( componentModel = "spring")
public abstract class MessageMapper {

    @Autowired
    private IUserRepository userRepository;




        @Mapping(target = "id", ignore = true)
        @Mapping(target = "timestamp", ignore = true)
        @Mapping(target = "senderId", ignore = true)
        public abstract Message toDocument (CreateMessageRequestDto createMessageRequestDto);

        @Mapping(target = "senderName", ignore = true)
        public abstract ResponseMessageDto toResponse (Message message);

        public abstract List<ResponseMessageDto> toResponseList (List < Message > messageList);

        @AfterMapping
        protected void completeMessageResponse (Message message, @MappingTarget ResponseMessageDto response){
            Optional<User> senderOpt = userRepository.findById(message.getSenderId());
            senderOpt.ifPresent(sender -> response.setSenderName(sender.getName()));

        }
    }

