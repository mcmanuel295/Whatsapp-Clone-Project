package com.mcmanuel.Whatsapp_clone.chat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat,String> {
    @Query(name = ChatConstant.FIND_CHAT_BY_SENDER_ID)
    List<Chat> findChatsBySenderId(String userId);
}
