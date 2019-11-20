package com.flashex.ordermicroservice.workerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(scanBasePackages = "com.flashex.ordermicroservice")
@RestController
@EnableDiscoveryClient
public class DemoApplication {



    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }


}