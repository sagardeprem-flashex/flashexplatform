package com.flashex.triptrackingmicroservice.lib.services;

import com.flashex.triptrackingmicroservice.lib.model.TripLog;
import com.flashex.triptrackingmicroservice.lib.repository.TripLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TripLogService {

    @Autowired
    private TripLogRepository tripLogRepository;

    public List<TripLog> getAllTripLog(){
        return tripLogRepository.findAll();
    }

    public ArrayList<TripLog> saveTripLogs(ArrayList<TripLog> tripLogs){
        ArrayList<TripLog> savedTripLogs = new ArrayList<>();
        for (TripLog tripLog : tripLogs) {
            tripLog.setTripItineraryId(UUID.randomUUID().toString());
            savedTripLogs.add(this.tripLogRepository.save(tripLog));
        }
        return savedTripLogs;
    }
}
