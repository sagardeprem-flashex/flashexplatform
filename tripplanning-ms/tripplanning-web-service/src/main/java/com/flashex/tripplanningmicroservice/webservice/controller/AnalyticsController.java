package com.flashex.tripplanningmicroservice.webservice.controller;


import com.flashex.tripplanningmicroservice.lib.model.DaySummary;
import com.flashex.tripplanningmicroservice.lib.services.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api/v1")
public class AnalyticsController {

    @Autowired
    AnalyticsService analyticsService;

    @GetMapping("/generated-summary")
    public DaySummary getSummary(@RequestParam String date){
        return analyticsService.getSummaryForDay(date);
    }
}
