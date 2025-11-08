package com.example.demo.user.mapper;

import java.util.List;

import com.example.demo.user.User;
import com.example.demo.user.dto.CreateUserRequestDto;
import com.example.demo.user.dto.ResponseUserDto;
import com.example.demo.user.dto.UpdateUserDto;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;


@Mapper(componentModel = "spring")
public interface UserMapper {



	@Mapping( target = "id", ignore = true)
	User toDocument(CreateUserRequestDto createUserDto);
	
	ResponseUserDto toResponse (User user);
	
	List<ResponseUserDto> toResponseList(List<User> userList);
    @Mapping( target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void updateUserFromDto(UpdateUserDto updateUserDto, @MappingTarget User user);

}
