package com.flashex.triptrackingmicroservice.lib.services;

import com.flashex.triptrackingmicroservice.lib.model.TripsDetails;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

@Service
@EnableConfigurationProperties(ServiceProperties.class)
public class TrackingService {
    private final ServiceProperties serviceProperties;

    public TrackingService(ServiceProperties serviceProperties) {
        this.serviceProperties = serviceProperties;
    }

    public String message() {
        return this.serviceProperties.getMessage();
    }


}
