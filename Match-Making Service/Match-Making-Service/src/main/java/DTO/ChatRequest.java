package DTO;

import java.time.Instant;

    public record ChatRequest(
            String username,
            Instant sentAt,
            String receiverId,
            String messageText,
            String SenderId,
            String conversationId,
            boolean IsSend) {
    }

