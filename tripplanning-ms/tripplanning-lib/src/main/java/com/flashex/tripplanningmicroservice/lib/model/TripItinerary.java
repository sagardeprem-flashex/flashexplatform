package com.flashex.tripplanningmicroservice.lib.model;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TripItinerary {

    private String tripItineraryId;
    private List<Packet> packets;
    private Date plannedStartTime;
    private Date plannedEndTime;
    private float plannedTotalDistance;
    private Vehicle vehicle;
    private float tripExpense;
    private float occupiedVolume;

}
