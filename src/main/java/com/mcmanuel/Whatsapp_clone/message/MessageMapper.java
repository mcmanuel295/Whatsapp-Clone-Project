package com.mcmanuel.Whatsapp_clone.message;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageMapper {

    public MessageResponse toMessageResponse(Message message) {
        return MessageResponse.builder()
                .id(message.getId())
                .content(message.getContent())
                .senderId(message.getSenderId())
                .receiverId(message.getReceiverId())
                .type(message.getMessageType())
                .state(message.getMessageState())
                .createdAt(message.getCreatedDate())

//                todo read the media file
                .build();

    }
}
