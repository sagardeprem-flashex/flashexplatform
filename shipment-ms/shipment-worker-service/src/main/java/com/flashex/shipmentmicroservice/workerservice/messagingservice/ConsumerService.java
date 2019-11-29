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

@Service
public class ConsumerService {
    private final Logger logger = LoggerFactory.getLogger(ConsumerService.class);

    @Autowired
    BinningService binningService;

    @KafkaListener(topics = "Order", groupId = "group_id")
    public void consume(String message) throws JsonProcessingException {
        logger.info(String.format("$$ -> Binning received order -> %s",message));
        Packet packet = new ObjectMapper().readValue(message, Packet.class);
        binningService.binPacket(packet);
    }
}
