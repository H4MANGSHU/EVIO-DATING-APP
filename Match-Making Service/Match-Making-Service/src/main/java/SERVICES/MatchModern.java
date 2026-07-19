package SERVICES;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.FileNotFoundException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MatchModern {

    private final WebClient webClient;


    public Mono<@NonNull String> match(MultipartFile a, MultipartFile b) throws FileNotFoundException {

        MultipartBodyBuilder builder = new MultipartBodyBuilder();

        builder.part("image1", a.getResource())
                .filename(Objects.requireNonNull(a.getOriginalFilename()));

        builder.part("image2", b.getResource())
                .filename(Objects.requireNonNull(b.getOriginalFilename()));

        return webClient.post()
                .uri("/predict")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(builder.build()))
                .retrieve()
                .bodyToMono(String.class);
    }
}
