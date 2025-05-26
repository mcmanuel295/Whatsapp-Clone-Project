package com.mcmanuel.Whatsapp_clone.message;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message,Long> {
    @Query(name = MessageConstant.FIND_MESSAGES_BY_CHAT_ID)
    Optional<List<Message>> findMessagesByChatId(String chatId);


//    @Query(name = MessageConstant.SET_MESSAGES_TO_BE_SEEN_BY_CHAT)
}
