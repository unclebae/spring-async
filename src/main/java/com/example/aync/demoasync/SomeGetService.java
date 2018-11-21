package com.example.aync.demoasync;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class SomeGetService {

    private final RestTemplate restTemplate;

    public SomeGetService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<String> someGetFromWiki(String word) throws InterruptedException {
        log.info("Find Word: {}", word);
        String url = "https://api.github.com/users/" + word;
        final String forObject = restTemplate.getForObject(url, String.class);
        Thread.sleep(1000L);

        return CompletableFuture.completedFuture(forObject);

    }

}
