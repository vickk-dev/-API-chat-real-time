package com.example.demo.chat.dto;


import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CreateChatDto {

    String chatId;
    String receiverId;
    String receiverEmail;
    String name;
    String senderId;
    String senderName;


}
