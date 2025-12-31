package com.example.demo.chat.dto;


import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CreateChatDto {

    String chatId;
    String id;
    String name;
    String senderId;
    String senderName;


}
