package com.flashex.triptrackingmicroservice.webservice.trackingcontroller;

import com.flashex.triptrackingmicroservice.lib.services.SampleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class TrackingController {

    private SampleService sampleService;

    public TrackingController(SampleService sampleService) {
        this.sampleService = sampleService;
    }

}
