package com.mcmanuel.Whatsapp_clone.message;

import com.mcmanuel.Whatsapp_clone.chat.Chat;
import com.mcmanuel.Whatsapp_clone.chat.ChatRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    private final ChatRepository chatRepository;
    private final MessageMapper mapper;

    public void saveMessage(MessageRequest messageRequest){
        Chat chat =chatRepository.findById(messageRequest.getChatId()).orElseThrow(
                ()-> new EntityNotFoundException("Chat with id "+messageRequest.getChatId()+ " wasn't found")
        );

        Message message = new Message();

        message.setContent(messageRequest.getContent());
        message.setChat(chat);
        message.setSenderId(messageRequest.getSenderId());
        message.setReceiverId(messageRequest.getReceiverId());
        message.setMessageType(messageRequest.getMessageType());
        message.setMessageState(MessageState.SENT);

        messageRepository.save(message);
//        todo notification
    }

    public List<MessageResponse> findChatMessages(String chatId){
        return messageRepository.findMessagesByChatId(chatId)
                .stream()
                .map(mapper::toMessageResponse)
                .toList();
    }


    public void setMessagesToSeen(String chatId, Authentication authentication){
        Chat chat = chatRepository.findById(chatId).orElseThrow(
                ()-> new EntityNotFoundException("Chat not found"));

        final String recipientId = getRecipientId(chat,authentication);
    }

    private String getRecipientId(Chat chat, Authentication authentication) {
        if (chat.getSender().getId().equals( authentication.getName())){
            return chat.getRecipient().getId();
        }
        return chat.getSender().getId();

    }
}
