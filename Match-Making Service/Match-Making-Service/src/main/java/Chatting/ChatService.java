package Chatting;

import DTO.ChatRequest;
import DTO.PreferencesDTO;
import DTO.ScoreDTO;
import SCORE_SERVICE.ModernScore;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ChatService {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private ModernScore modernScore;
    public void createRoom(ChatRequest chatRequest, ScoreDTO scoreDTO, PreferencesDTO preferencesDTO) {

        if (modernScore.TotalScore(scoreDTO, preferencesDTO) >= 0.93) {
            simpMessagingTemplate.convertAndSendToUser(chatRequest.SenderId(), "/queue/messages", chatRequest);
            simpMessagingTemplate.convertAndSendToUser(chatRequest.receiverId(),"/queue/messages",chatRequest);
        }

    }
}
