package com.flashex.tripplanningmicroservice.workerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(scanBasePackages = "com.flashex.tripplanningmicroservice")
@RestController
public class DemoApplication {



    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }


}
