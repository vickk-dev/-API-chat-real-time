package com.example.demo.usuario.mapper;

import java.util.List;

import com.example.demo.usuario.User;
import com.example.demo.usuario.dto.CreateUserRequestDto;
import com.example.demo.usuario.dto.ResponseUserDto;
import com.example.demo.usuario.dto.UpdateUserDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;


import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper(componentModel = "spring")
public interface UserMapper {
	
	
	User toDocument(CreateUserRequestDto createUserDto);
	
	ResponseUserDto toResponse (User user);
	
	List<ResponseUserDto> toResponseList(List<User> usuario);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void updateUserFromDto(UpdateUserDto updateUsuario, @MappingTarget User user);

}
