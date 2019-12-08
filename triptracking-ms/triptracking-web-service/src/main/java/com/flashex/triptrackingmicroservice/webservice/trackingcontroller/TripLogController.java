package com.flashex.triptrackingmicroservice.webservice.trackingcontroller;

import com.flashex.triptrackingmicroservice.lib.model.TripLog;
import com.flashex.triptrackingmicroservice.lib.repository.TripLogRepository;
import com.flashex.triptrackingmicroservice.lib.services.TripLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class TripLogController {

    @Autowired
    private TripLogService tripLogService;

    @Autowired
    private TripLogRepository tripLogRepository;

    @GetMapping("/triplogs")
    public List<TripLog> getAllTripLogs(){
        return tripLogService.getAllTripLog();
    }

    @GetMapping("/triplog")
    public TripLog getTripLogById( @RequestParam UUID id){
        return tripLogService.getTripLog(id);
    }

    @PostMapping("/triplogs")
    public List<TripLog> saveTripLogs(@RequestBody List<TripLog> tripLogs ){
        return tripLogService.saveTripLogs(tripLogs);
    }

    @PutMapping("/updatelogs")
    public TripLog updateLogs(@RequestParam UUID id, @RequestBody TripLog tripLog){
        return tripLogService.updateTripLog(id,tripLog);
    }
}
