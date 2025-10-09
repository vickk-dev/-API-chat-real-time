package com.example.demo.user.service;

import com.example.demo.user.IUserRepository;

import com.example.demo.user.User;

import com.example.demo.user.dto.CreateUserRequestDto;
import com.example.demo.user.dto.ResponseUserDto;
import com.example.demo.user.dto.UpdateUserDto;
import com.example.demo.user.mapper.UserMapper;

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

    public List<ResponseUserDto> getAllUsers(){
        List<User> user = userRepository.findAll();

        return userMapper.toResponseList(user);

    }

    public ResponseUserDto getUserById(String id){
        Optional<User> user = Optional.of(userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario com o Id: " + id + " não encontrado")));

        return userMapper.toResponse(user.get());

    }

    public ResponseUserDto createUser(CreateUserRequestDto createUserRequestDto){
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
    public ResponseUserDto updateUser(String id, UpdateUserDto updateUserDto){
        User user = userRepository.findById(id)
               .orElseThrow(()-> new EntityNotFoundException("Usuario com o id: "+ id + "não encontrado"));
           if (!user.getEmail().equals(updateUserDto.getEmail())) {
               Optional<User> userNewEmail = userRepository.findByEmail(updateUserDto.getEmail());
               if (userNewEmail.isPresent() && !userNewEmail.get().getId().equals(id)) {
                   throw new IllegalArgumentException("Usuario com o e-mail existente: " + updateUserDto.getEmail());

               }

               userMapper.updateUserFromDto(updateUserDto, user);

           }

        User saveUser = userRepository.save(user);


        return userMapper.toResponse(saveUser);
    }


    public void deleteUser(String id) {
        if (userRepository.findById(id).isPresent()) {
            throw new RuntimeException("Usuario não encontrado");

        }
        userRepository.deleteById(id);
    }
}
