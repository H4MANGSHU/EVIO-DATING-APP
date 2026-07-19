package Entites;

import Enums.PLAN;
import co.elastic.clients.util.DateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "subscription")
public class Subscription {
    private String subId;
    private LocalDateTime StartPlan;
    private LocalDateTime EndPlan;
    private PLAN PlanStatus;
    private String UserId;
    private boolean isSub;

}
