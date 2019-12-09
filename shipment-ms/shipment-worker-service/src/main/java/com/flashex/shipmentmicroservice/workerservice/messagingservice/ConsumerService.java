package com.flashex.shipmentmicroservice.workerservice.messagingservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flashex.shipmentmicroservice.lib.model.Packet;
import com.flashex.shipmentmicroservice.lib.services.BinningService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.flashex.shipmentmicroservice.lib.services.PacketService;
import java.util.Collections;



@Service
public class ConsumerService {
    private final Logger logger = LoggerFactory.getLogger(ConsumerService.class);

    @Autowired
    BinningService binningService;

    @Autowired
    private PacketService packetService;

    @KafkaListener(topics = "Order", groupId = "group_id")
    public void consume(String message) throws JsonProcessingException {
        Packet packet = new ObjectMapper().readValue(message, Packet.class);
        logger.info(String.format("$$ ->Successfully parsed received order, binning received order -> %s",message));
	packetService.savePackets(Collections.singletonList(packet));
        logger.info(String.format("$$ ->Successfully saved to db ->"));
        binningService.binPacket(packet);
    }
}
