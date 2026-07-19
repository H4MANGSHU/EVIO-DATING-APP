package DTOS;

import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "aws.s3")
public class AwsCred {
    private String Regions;
    private String BucketName;
    private String AccessKey;
    private String SecKey;
}
