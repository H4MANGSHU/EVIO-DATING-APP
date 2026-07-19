import org.apache.kafka.common.internals.Topic;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;

@EnableKafka
@Configuration
public class KafkaConfig {
    
    private TopicBuilder topic(){
        return TopicBuilder
                .name("DLT-handler")
                .partitions(4)
                .replicas(4)
                .compact();
    }
}
