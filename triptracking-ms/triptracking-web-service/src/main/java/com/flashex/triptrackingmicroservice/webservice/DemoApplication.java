package com.flashex.triptrackingmicroservice.webservice;

import com.flashex.triptrackingmicroservice.lib.services.TrackingService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.flashex.triptrackingmicroservice")
@RestController
public class DemoApplication {

    private final TrackingService trackingService;

    public DemoApplication(TrackingService trackingService) {
        this.trackingService = trackingService;
    }

    @GetMapping("/home")
    public String home() {
        return trackingService.message();
    }
    @GetMapping("/")
    public String home1() {
        return "BLANK";
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
