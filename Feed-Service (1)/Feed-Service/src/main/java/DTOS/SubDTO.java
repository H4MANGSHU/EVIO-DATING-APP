package DTOS;

import Enums.PLAN;
import co.elastic.clients.util.DateTime;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class SubDTO {
    private long subId;
    private LocalDateTime StartPlan;
    private LocalDateTime EndPlan;
    private PLAN PlanStatus;
    private String UserId;
    private boolean isSub;
}
