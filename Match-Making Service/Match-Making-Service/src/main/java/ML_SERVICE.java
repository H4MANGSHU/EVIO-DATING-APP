import io.netty.channel.ChannelOption;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;

@Configuration
public class ML_SERVICE {



    HttpClient httpClient = HttpClient.create()
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS,5000)
            .responseTimeout(Duration.ofSeconds(40));

    @Bean
    private WebClient webClient(){
        return WebClient.builder()
                .baseUrl("https://huggingface.co/spaces/wreckerseee/fuck")
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }

}
