package com.mcmanuel.Whatsapp_clone.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;

    @Transactional(readOnly = true)
    public List<ChatResponse> findAllChatsByReciverId(Authentication currentUser){
        final String userId =currentUser.getName();
        return chatRepository.findChatsBySenderId(userId);
    }

    public Chat createChat(){
        Chat newChat = new Chat();

        chatRepository.save(newChat);
        return newChat;
    }

}
