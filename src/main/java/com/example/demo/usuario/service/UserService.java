package com.example.demo.usuario.service;

import com.example.demo.usuario.IUserRepository;

import com.example.demo.usuario.User;

import com.example.demo.usuario.dto.CreateUserRequestDto;
import com.example.demo.usuario.dto.ResponseUserDto;
import com.example.demo.usuario.dto.UpdateUserDto;
import com.example.demo.usuario.mapper.UserMapper;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {


    private final IUserRepository userRepository;

    private final UserMapper userMapper;

    public List<ResponseUserDto> getAllUsuarios(){
        List<User> usuarios = userRepository.findAll();

        return userMapper.toResponseList(usuarios);

    }

    public ResponseUserDto getUsuarioById(String id){
        Optional<User> usuario = Optional.of(userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario com o Id: " + id + " não encontrado")));

        return userMapper.toResponse(usuario.get());

    }

    public ResponseUserDto createUsuario(CreateUserRequestDto createUserRequestDto){
        userRepository.findByEmail(createUserRequestDto.getEmail())
                .ifPresent(u -> {
                    throw new IllegalArgumentException("Um usuario com o e-mail : " + createUserRequestDto
                            .getEmail());
        });

        User novoUsuario = userMapper.toDocument(createUserRequestDto);

        User usuarioSalvo = userRepository.save(novoUsuario);
        return userMapper.toResponse(usuarioSalvo);
    }
    //Faz a atualiza as informações do usuario
    public ResponseUserDto updateUsuario(String id, UpdateUserDto updateUserDto){
        User usuario = userRepository.findById(id)
               .orElseThrow(()-> new EntityNotFoundException("Usuario com o id: "+ id + "não encontrado"));
           if (!usuario.getEmail().equals(updateUserDto.getEmail())) {
               Optional<User> userNewEmail = userRepository.findByEmail(updateUserDto.getEmail());
               if (userNewEmail.isPresent() && !userNewEmail.get().getId().equals(id)) {
                   throw new IllegalArgumentException("Usuario com o e-mail existente: " + updateUserDto.getEmail());

               }

               userMapper.updateUserFromDto(updateUserDto, usuario);

           }

        User usuarioSalvo = userRepository.save(usuario);


        return userMapper.toResponse(usuarioSalvo);
    }

}
