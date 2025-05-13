package com.mcmanuel.Whatsapp_clone.message;

import com.mcmanuel.Whatsapp_clone.chat.Chat;
import com.mcmanuel.Whatsapp_clone.entity.BaseAuditingEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "messages")
public class Messages extends BaseAuditingEntity {

    @Id
    @SequenceGenerator(name = "msg_seq",sequenceName = "msg_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "msg_seq")
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Enumerated(EnumType.STRING)
    private MessageState messageState;

    @Enumerated(EnumType.STRING)
    private MessageType messageType;

    @ManyToOne
    @JoinColumn(name = "chat_id")
    private Chat chat;

    @Column(name = "sender_id",nullable = false)
    private String senderId;

    @Column(name = "receiver_id",nullable = false)
    private String receiverId;

}
