package com.mcmanuel.Whatsapp_clone.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;
    private final ChatMapper chatMapper;

    @Transactional(readOnly = true)
    public List<ChatResponse> findAllChatsByReciverId(Authentication currentUser){
        final String userId =currentUser.getName();

        return chatRepository.findChatsBySenderId(userId)
                .stream()
                .map(chat -> chatMapper.toChatResponse(chat,userId))
                .toList();
    }

    public String createChat(String senderId,String receiverId){
        Optional<Chat> existingChat =chatRepository.findChatsByReceiver(senderId,receiverId);

        if(existingChat.isPresent()){
            return existingChat.get().getId();
        }
    }

    public Chat createChat(){
        Chat newChat = new Chat();

        chatRepository.save(newChat);
        return newChat;
    }
}
