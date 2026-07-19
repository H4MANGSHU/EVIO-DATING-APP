package CONFIGURATIONS;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@EnableKafka
public class Topics {

    @Bean
    public NewTopic newTopic(){
        return TopicBuilder.name("Payment-topic")
                .replicas(3)
                .partitions(2)
                .compact()
                .build();
    }
}
