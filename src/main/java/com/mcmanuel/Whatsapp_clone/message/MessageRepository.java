package com.mcmanuel.Whatsapp_clone.message;

import com.mcmanuel.Whatsapp_clone.chat.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message,Long> {
    @Query(name = MessageConstant.FIND_MESSAGES_BY_CHAT_ID)
    List<Message>  findMessagesByChatId(String chatId);

    @Query(name = MessageConstant.SET_MESSAGES_TO_BE_SEEN_BY_CHAT)
    @Modifying
    void setMessagesToSeenByChatId(@Param("chatId") String chatId,@Param("newState") MessageState state);


//    @Query(name = MessageConstant.SET_MESSAGES_TO_BE_SEEN_BY_CHAT)
}
