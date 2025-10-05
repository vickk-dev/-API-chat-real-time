package com.example.demo.usuario.controller;

import com.example.demo.usuario.dto.CreateUserRequestDto;
import com.example.demo.usuario.dto.ResponseUserDto;
import com.example.demo.usuario.dto.UpdateUserDto;
import com.example.demo.usuario.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

    final UserService userService;

    @PostMapping
    public ResponseEntity<ResponseUserDto> addUser(@Valid @RequestBody CreateUserRequestDto createUserRequestDto) {

        ResponseUserDto newUser = userService.createUser(createUserRequestDto);
        return ResponseEntity.status(201).body(newUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseUserDto> getUser(@PathVariable String id) {
       ResponseUserDto newUser = userService.getUserById(id);
       return ResponseEntity.status(200).body(newUser);

    }
    @PutMapping("/{id}")
    public ResponseEntity<ResponseUserDto> updateUser(@PathVariable String id, @Valid @RequestBody UpdateUserDto updateUserDto) {
        ResponseUserDto newUser =  userService.updateUser(id, updateUserDto);
        return ResponseEntity.status(200).body(newUser);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
