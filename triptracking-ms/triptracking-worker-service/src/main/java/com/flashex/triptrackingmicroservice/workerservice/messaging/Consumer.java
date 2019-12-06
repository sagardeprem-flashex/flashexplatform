package com.flashex.triptrackingmicroservice.workerservice.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flashex.triptrackingmicroservice.lib.model.Packet;
import com.flashex.triptrackingmicroservice.lib.model.PacketLog;
import com.flashex.triptrackingmicroservice.lib.model.TripItinerary;
import com.flashex.triptrackingmicroservice.lib.model.TripLog;
import com.flashex.triptrackingmicroservice.lib.services.TripLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class Consumer {
    private final Logger logger = LoggerFactory.getLogger(Consumer.class);

    @Autowired
    TripLogService tripLogService;

    @KafkaListener(topics = "TripItinerary", groupId = "group_id")
    public void consume(String message) throws JsonProcessingException {
        logger.info(String.format("$$ -> Consumed Message -> %s",message));
        logger.info(String.format("$$ -> Consumed Message -> %s",new ObjectMapper().readValue(message, TripItinerary.class)));
        TripItinerary tripItinerary = new ObjectMapper().readValue(message, TripItinerary.class);

        // create a log
        TripLog tripLog = new TripLog();
        tripLog.setTripItineraryId(tripItinerary.getTripItineraryId());
        tripLog.setOriginAddress(tripItinerary.getOriginAddress());
        tripLog.setPlannedStartTime(tripItinerary.getPlannedStartTime());
        tripLog.setPlannedEndTime(tripItinerary.getPlannedEndTime());

        // extract data from packets to packetlogs
        List<PacketLog> packetLogs = new ArrayList<>();

        for(int i=0; i<tripItinerary.getPackets().size(); i++){
            PacketLog packetLog = new PacketLog();
            packetLog.setPacketId(tripItinerary.getPackets().get(i).getPacketId());
            packetLog.setDeliveryAddress(tripItinerary.getPackets().get(i).getDeliveryAddress());
            packetLog.setDeliveryDescription(tripItinerary.getPackets().get(i).getDeliveryDescription());
            packetLogs.add(packetLog);

        }

        tripLog.setPacketLogs(packetLogs);

        // save to database
        tripLogService.saveTripLogs(Collections.singletonList((tripLog)));

    }
}
