package com.example.aync.demoasync;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StopWatch;

import java.util.concurrent.CompletableFuture;

@Slf4j
@SpringBootApplication
public class DemoAsyncApplication implements CommandLineRunner {

    @Autowired
    private SomeGetService someGetService;

    public static void main(String[] args) {
        SpringApplication.run(DemoAsyncApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start("Call Async");
        CompletableFuture<String> get1 = someGetService.someGetFromWiki("PivotalSoftware");
        CompletableFuture<String> get2 = someGetService.someGetFromWiki("CloudFoundry");
        CompletableFuture<String> get3 = someGetService.someGetFromWiki("Spring-Projects");
        CompletableFuture<String> get4 = someGetService.someGetFromWiki("RameshMF");

        CompletableFuture.allOf(get1, get2, get3, get4).join();

        stopWatch.stop();
        log.info("Elapsed Time: {} ms", stopWatch.getTotalTimeMillis());
        log.info("GET1 : {}", get1.get());
        log.info("GET2 : {}", get2.get());
        log.info("GET3 : {}", get3.get());
        log.info("GET4 : {}", get4.get());

    }
}
