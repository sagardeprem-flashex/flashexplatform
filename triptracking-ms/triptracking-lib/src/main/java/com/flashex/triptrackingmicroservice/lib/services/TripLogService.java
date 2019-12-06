package com.flashex.triptrackingmicroservice.lib.services;

import com.flashex.triptrackingmicroservice.lib.model.PacketLog;
import com.flashex.triptrackingmicroservice.lib.model.TripLog;
import com.flashex.triptrackingmicroservice.lib.repository.TripLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TripLogService {

    @Autowired
    private TripLogRepository tripLogRepository;


    public List<TripLog> getAllTripLog(){
        return tripLogRepository.findAll();
    }


    public List<TripLog> saveTripLogs(List<TripLog> tripLogs){
        List<TripLog> savedTripLogs = new ArrayList<>();

        for (TripLog tripLog : tripLogs) {
            tripLog.setTripItineraryId(UUID.randomUUID().toString());
            savedTripLogs.add(this.tripLogRepository.save(tripLog));
        }
        return savedTripLogs;
    }
    public TripLog updateTripLog(UUID id, TripLog tripLog) {
        TripLog tripLog1 = tripLogRepository.findById(id).orElse(null);
        tripLog1.setTripStart(tripLog.getTripStart());
        tripLog1.setTripEnd(tripLog.getTripEnd());
        TripLog updateTripLog = tripLogRepository.save(tripLog1);
        return updateTripLog;
    }
}
