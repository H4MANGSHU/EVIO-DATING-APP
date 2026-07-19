package DTOS;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDTO {
    private String Caption; //5
    private String Description;  //1
    private String MediaUrl;  //2
    private Instant PostedAt; //3

}
