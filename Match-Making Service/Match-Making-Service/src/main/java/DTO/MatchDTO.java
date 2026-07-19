package DTO;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class MatchDTO {

    private final WebClient webClient;

    public Mono<@NonNull String> Match(MultipartFile A,MultipartFile B){
        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("photoA",A.getResource())
                .filename(Objects.requireNonNull(A.getOriginalFilename()));
           builder.part("PhotoB",B.getResource())
         .filename(Objects.requireNonNull(B.getOriginalFilename()));

        return webClient.post()
                .uri("/predict")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(builder.build()))
                .retrieve()
                .bodyToMono(String.class)
                .onErrorResume(e-> {
                    log.error("api failed {}", e.getMessage());
                    return Mono.just("wait sometime");
                });
                }
    }

