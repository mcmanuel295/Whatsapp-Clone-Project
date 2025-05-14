package com.mcmanuel.Whatsapp_clone.chat;

import com.mcmanuel.Whatsapp_clone.message.MessageState;
import com.mcmanuel.Whatsapp_clone.message.MessageType;
import com.mcmanuel.Whatsapp_clone.message.Messages;
import com.mcmanuel.Whatsapp_clone.entity.BaseAuditingEntity;
import com.mcmanuel.Whatsapp_clone.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

import static jakarta.persistence.GenerationType.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "chats")
public class Chat extends BaseAuditingEntity {
    @Id
    @GeneratedValue(strategy = UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private User recipient;

    @OneToMany(mappedBy = "chat",fetch = FetchType.EAGER)
    @OrderBy("createdDate DESC")
    private List<Messages> messages;

    @Transient
    public String getChatName(final String senderId ){
        if (recipient.getId().equals(senderId)){
            return sender.getFirstName()+" "+sender.getLastName();
        }
        return recipient.getFirstName()+" "+recipient.getLastName();
    }

    @Transient
    public long getUnreadMessages(final String senderId){
        return messages.stream()
                .filter(message-> message.getReceiverId().equals(senderId))
                .filter(m-> MessageState.SENT == m.getMessageState())
                .count();
    }

    @Transient
    public String getLastMessage(String senderId){
       if (messages!=null && !messages.isEmpty()){
           if (messages.getFirst().getMessageType()!= MessageType.TEXT){
               return "Attachment";
           }
       }
       return null;
    }

    @Transient
    public LocalDateTime getLastMessageTime(String senderId){
        if (messages!=null && !messages.isEmpty()){
            return messages.getFirst().getCreatedDate();
        }
        return null;
    }
}
