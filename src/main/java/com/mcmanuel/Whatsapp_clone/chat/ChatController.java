package com.mcmanuel.Whatsapp_clone.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/chats")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @PostMapping
    public ResponseEntity<String> createChat(
            @RequestParam("sender_id") String senderId,
            @RequestParam(name = "receiver_id") String receiverId){

        final String chatId = chatService.createChat(senderId,receiverId);

        return new ResponseEntity<>(chatId, HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<List<ChatResponse>> findAllChatsByReceiverId(Authentication currentUser){
        return new ResponseEntity<>(chatService.findAllChatsByReceiverId(currentUser),HttpStatus.OK);
    }
}
