package com.flashex.tripplanningmicroservice.webservice;

import com.flashex.tripplanningmicroservice.lib.services.ORService;
import com.flashex.tripplanningmicroservice.lib.services.SampleService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.flashex.tripplanningmicroservice")
@RestController
public class TripPlanningWorkerServ {

    private final SampleService sampleService;
    private final ORService orService;

    public TripPlanningWorkerServ(SampleService sampleService, ORService orService) {
        this.sampleService = sampleService;
        this.orService = orService;
    }

    @GetMapping("/")
    public String home() {
        return sampleService.message();
    }

    @GetMapping("/VRPCapCons")
    public void VrpCapCons() throws Exception {
         orService.VrpfunctionWithCapCons();
    }

    @GetMapping("/VRPNodeDrop")
    public void VrpNodeDrop() throws Exception {
        orService.VrpfuncWithDropNode();
    }

    @GetMapping("/VRPTimeWindow")
    public void VrpTimeWindow() throws Exception {
        orService.TimeWindowConsFunction();
    }


    public static void main(String[] args) {
        SpringApplication.run(TripPlanningWorkerServ.class, args);
    }
}
