package com.mcmanuel.Whatsapp_clone.chat;

import com.mcmanuel.Whatsapp_clone.messages.Messages;
import com.mcmanuel.Whatsapp_clone.entity.BaseAuditingEntity;
import com.mcmanuel.Whatsapp_clone.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    private User sender;
    private User myRecipient;

    private List<Messages> messages;

}
