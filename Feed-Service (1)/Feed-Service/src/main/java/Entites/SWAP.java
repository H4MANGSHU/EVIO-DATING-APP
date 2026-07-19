package Entites;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "swap")
@Builder
public class SWAP {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String SwapId;
    private String UserId;
    private int Swap;
}
