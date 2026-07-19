package ENTITY;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "chat_table")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID ChatId;
    @Column(name = "name",nullable = false)
    private String username;
    @Column(name = "time",nullable = false)
    private Instant SendAt;
    @Column(name = "receiver_id",nullable = false)
    private String ReceiverId;
    @Column(columnDefinition = "text",nullable = false)
    private String SendId;
    @Column(columnDefinition = "text",nullable = false)
    private  String Messages;
    @Column(name = "convo_id",nullable = false)
    private  String ConversationId;
    @Column(name = "is_send")
    private boolean IsSend= false;

}
