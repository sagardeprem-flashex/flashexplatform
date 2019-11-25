package com.flashex.shipmentmicroservice.webservice.controller;



import com.flashex.shipmentmicroservice.lib.services.SampleService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@EnableDiscoveryClient
@SpringBootApplication(
        scanBasePackages = {"com.flashex.shipmentmicroservice"}
        )
//@EnableCassandraRepositories("com.flashex.shipmentmicroservice.lib.repository")
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
