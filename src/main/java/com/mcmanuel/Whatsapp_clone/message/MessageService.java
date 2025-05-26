package com.mcmanuel.Whatsapp_clone.message;

import com.mcmanuel.Whatsapp_clone.chat.Chat;
import com.mcmanuel.Whatsapp_clone.chat.ChatRepository;
import com.mcmanuel.Whatsapp_clone.file.FileService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    private final ChatRepository chatRepository;
    private final MessageMapper mapper;
    private final FileService fileService;

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


    @Transactional
    public void setMessagesToSeen(String chatId, Authentication authentication){
        Chat chat = chatRepository.findById(chatId).orElseThrow(
                ()-> new EntityNotFoundException("Chat not found"));

//        final String recipientId = getRecipientId(chat,authentication);
        messageRepository.setMessagesToSeenByChatId(chatId,MessageState.SEEN);

//        todo send notification
    }


    public void uploadMedia(String chatId, MultipartFile file,Authentication authentication){
        Chat chat = chatRepository.findById(chatId).orElseThrow(
                ()-> new EntityNotFoundException("Chat not found"));

        final String senderId = getSenderId(chat,authentication);
        final String recipientId = getRecipientId(chat,authentication);

        final String filePath = fileService.saveFile(file,senderId);

        Message message = new Message();

        message.setChat(chat);
        message.setSenderId(senderId);
        message.setReceiverId(recipientId);
        message.setMessageType(MessageType.IMAGE);
        message.setMessageState(MessageState.SENT);
        message.setMediaFilePath(filePath);

        messageRepository.save(message);

//        todo notification
    }



    private String getSenderId(Chat chat, Authentication authentication) {

        if (chat.getSender().getId().equals(authentication.getName())) {
            return  chat.getSender().getId();
        }
        return chat.getRecipient().getId();
    }


    private String getRecipientId(Chat chat, Authentication authentication) {
        if (chat.getSender().getId().equals( authentication.getName() )){
            return chat.getRecipient().getId();
        }
        return chat.getSender().getId();
    }
}
