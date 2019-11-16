package com.flashex.ordermicroservice.lib.services;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@EnableConfigurationProperties(ServiceProperties.class)
public class SampleService {
    private final ServiceProperties serviceProperties;

    public SampleService(ServiceProperties serviceProperties) {
        this.serviceProperties = serviceProperties;
    }

    public String message() {
        return this.serviceProperties.getMessage();
    }

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<String> AsyncMethod() throws InterruptedException {
        Thread.sleep(5000);
        return CompletableFuture.completedFuture(this.serviceProperties.getMessage());
    }
}
