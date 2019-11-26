package com.flashex.tripplanningmicroservice.lib.services;

import com.flashex.tripplanningmicroservice.lib.model.TripItinerary;
import com.flashex.tripplanningmicroservice.lib.repository.TripItineraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TripItineraryService {

    @Autowired
    private TripItineraryRepository tripItineraryRepository;

    public List<TripItinerary> getAllTripItineraries()
    {
        return this.tripItineraryRepository.findAll();
    }

    public TripItinerary saveTripItinerary(TripItinerary tripItinerary)
    {
        return  this.tripItineraryRepository.save(tripItinerary) ;
    }

}
