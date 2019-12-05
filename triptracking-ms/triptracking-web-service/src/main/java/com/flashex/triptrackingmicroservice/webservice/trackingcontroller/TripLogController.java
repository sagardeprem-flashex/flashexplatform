package com.flashex.triptrackingmicroservice.webservice.trackingcontroller;

import com.flashex.triptrackingmicroservice.lib.model.TripLog;
import com.flashex.triptrackingmicroservice.lib.services.TripLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TripLogController {

    @Autowired
    private TripLogService tripLogService;
    @GetMapping("/triplogs")
    public List<TripLog> getAllShipments(){
        System.out.println("In controller");
        return tripLogService.getAllTripLog();
    }

    @PostMapping("/triplogs")
    public List<TripLog> saveShipments(@RequestBody ArrayList<TripLog> tripLogs){
        System.out.println("In controller");
        return tripLogService.saveTripLogs(tripLogs);
    }
}
