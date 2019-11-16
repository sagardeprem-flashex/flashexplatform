package com.flashex.triptrackingmicroservice.workerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(scanBasePackages = "com.flashex.triptrackingmicroservice")
@RestController
public class DemoApplication {



    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }


}
