package com.flashex.ordertracking.messagingservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flashex.shipmentmicroservice.lib.model.KafkaStatusMessage;
import com.flashex.shipmentmicroservice.lib.model.Packet;
import com.flashex.shipmentmicroservice.lib.services.PacketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class ConsumerService {
    private final Logger logger = LoggerFactory.getLogger(ConsumerService.class);

    @Autowired
    private PacketService packetService;

    @KafkaListener(topics = "DeliveryStatus", groupId = "group_id")
    public void consume(String message) throws JsonProcessingException {
        logger.info(String.format("$$ -> Consumed Message -> %s",message));
        KafkaStatusMessage packetStatus = new ObjectMapper().readValue(message, KafkaStatusMessage.class);
//        packetService.savePackets(Collections.singletonList(packet));
        packetService.updateStatus(packetStatus);
    }
}
