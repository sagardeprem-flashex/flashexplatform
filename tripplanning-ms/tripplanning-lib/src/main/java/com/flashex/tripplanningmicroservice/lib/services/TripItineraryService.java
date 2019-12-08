package com.flashex.tripplanningmicroservice.lib.services;

import com.flashex.tripplanningmicroservice.lib.model.TripItinerary;
import com.flashex.tripplanningmicroservice.lib.repository.TripItineraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripItineraryService {

    @Autowired
    private TripItineraryRepository tripItineraryRepository;


    public TripItinerary getSpecificTripItinerary(String id) {
        return this.tripItineraryRepository.findById(id).orElse(null);
    }


    public List<TripItinerary> getAllTripItineraries()
    {
        return this.tripItineraryRepository.findAll();
    }

    public void saveTripItinerary(TripItinerary tripItinerary)
    {
        this.tripItineraryRepository.save(tripItinerary);
    }

    public void deleteTripItinerary(TripItinerary tripItinerary) {

        this.tripItineraryRepository.delete(tripItinerary);
    }


}
