package com.flashex.triptrackingmicroservice.lib.services;

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

//    public Optional<TripLog> getById(UUID id){
//        return tripLogRepository.findById(id);
//    }



    public List<TripLog> saveTripLogs(List<TripLog> tripLogs){

        for (TripLog tripLog : tripLogs) {
            List<TripLog> savedTripLogs = new ArrayList<>();
            if(tripLog.getTripItineraryId() == null){
                tripLog.setTripItineraryId(UUID.randomUUID().toString());
                tripLog.setTripStart(new Date());
                savedTripLogs.add(this.tripLogRepository.save(tripLog));
            }
        }
        return tripLogs;
    }

}
