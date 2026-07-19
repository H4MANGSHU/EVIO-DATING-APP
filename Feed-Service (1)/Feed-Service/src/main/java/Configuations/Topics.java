package Configuations;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@EnableKafka
public class Topics {

    private NewTopic newTopic(){
        return TopicBuilder.name("Payment_topic")
                .replicas(4)
                .partitions(2)
                .compact()
                .build();
    }
    private NewTopic DLTTopic(){
        return TopicBuilder.name("dlt_topic")
                .replicas(3)
                .partitions(2)
                .compact()
                .build();
    }
}
