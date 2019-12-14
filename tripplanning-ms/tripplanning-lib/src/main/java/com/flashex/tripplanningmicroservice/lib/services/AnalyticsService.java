package com.flashex.tripplanningmicroservice.lib.services;

import com.flashex.tripplanningmicroservice.lib.model.DaySummary;
import com.flashex.tripplanningmicroservice.lib.model.TripItinerary;
import com.flashex.tripplanningmicroservice.lib.repository.DaySummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class AnalyticsService {


    @Autowired
    TripItineraryService tripItineraryService;

    @Autowired
    DaySummaryRepository daySummaryRepository;

    @Scheduled(cron="0 0 11 * * *")
    public void scheduledSummaryCreation(){
        LocalDateTime today = LocalDateTime.now();
        createSummary(today.getYear(), today.getMonthValue(),today.getDayOfMonth());
    }

    public void createSummary(int year, int month, int day){
        List<TripItinerary> trips = tripItineraryService.getTripsByDay(year,month,day);

    }

    public DaySummary getSummaryForDay(String date){
        //String date = "13-08-2016";
        String[] values = date.split("-");
        int day = Integer.parseInt(values[0]);
        int month = Integer.parseInt(values[1]);
        int year = Integer.parseInt(values[2]);

        LocalDateTime localDateTime1 = LocalDateTime.of(year,month,day,0,0);
        LocalDateTime localDateTime2 = LocalDateTime.of(year,month,day+1,0,0);

        Date date1 = Date.from( localDateTime1.atZone( ZoneId.systemDefault()).toInstant());
        Date date2 = Date.from( localDateTime2.atZone( ZoneId.systemDefault()).toInstant());
        return daySummaryRepository.findAllByDateTimeBetween(date1, date2).get(0);
    }
}
