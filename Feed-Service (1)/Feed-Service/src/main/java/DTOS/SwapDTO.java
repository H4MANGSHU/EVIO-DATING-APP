package DTOS;


import jakarta.persistence.Entity;
import lombok.Data;

@Data
public class SwapDTO {
    private String SwapId;
    private String UserId;
    private int Swap;
}
