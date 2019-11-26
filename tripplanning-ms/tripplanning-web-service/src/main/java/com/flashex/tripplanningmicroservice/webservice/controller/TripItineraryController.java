package com.flashex.tripplanningmicroservice.webservice.controller;

import com.flashex.tripplanningmicroservice.lib.model.TripItinerary;
import com.flashex.tripplanningmicroservice.lib.services.TripItineraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TripItineraryController {

    @Autowired
    private TripItineraryService tripItineraryService;

    @GetMapping("/tripItineraries")
    public List<TripItinerary> getAllTripItineraries() {
        return tripItineraryService.getAllTripItineraries();
    }

    @PostMapping("/tripItineraries")
    public List<TripItinerary> saveTripItinerary(@RequestBody List<TripItinerary> tripItineraries)
    {
        tripItineraries.forEach(tripItinerary -> {
            tripItineraryService.saveTripItinerary(tripItinerary);
        });
        return tripItineraries;
    }

}
