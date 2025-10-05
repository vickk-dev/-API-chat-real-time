package com.example.demo.usuario.mapper;

import java.util.List;

import com.example.demo.usuario.User;
import com.example.demo.usuario.dto.CreateUserRequestDto;
import com.example.demo.usuario.dto.ResponseUserDto;
import com.example.demo.usuario.dto.UpdateUserDto;
import org.mapstruct.*;


@Mapper(componentModel = "spring")
public interface UserMapper {
	
	@Mapping( target = "id", ignore = true)
	User toDocument(CreateUserRequestDto createUserDto);
	
	ResponseUserDto toResponse (User user);
	
	List<ResponseUserDto> toResponseList(List<User> usuario);
    @Mapping( target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void updateUserFromDto(UpdateUserDto updateUsuario, @MappingTarget User user);

}
