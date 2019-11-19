package com.flashex.triptrackingmicroservice.webservice.trackingcontroller;

import com.flashex.triptrackingmicroservice.lib.services.TrackingService;
import com.flashex.triptrackingmicroservice.lib.model.TripsDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class TrackingController {

    private TrackingService trackingService;

    public TrackingController(TrackingService trackingService) {
        this.trackingService = trackingService;
    }

}
