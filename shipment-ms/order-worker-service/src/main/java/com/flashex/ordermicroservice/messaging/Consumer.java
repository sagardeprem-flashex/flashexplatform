package com.flashex.ordermicroservice.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flashex.shipmentmicroservice.lib.model.Packet;
import com.flashex.shipmentmicroservice.lib.services.PacketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {
    private final Logger logger = LoggerFactory.getLogger(Consumer.class);

//    @Autowired
//    private PacketService packetService;

    @KafkaListener(topics = "Batches", groupId = "group_id")
    public void consume(String message) throws JsonProcessingException {
        logger.info(String.format("$$ -> Consumed Message -> %s",message));
        Packet packet = new ObjectMapper().readValue(message, Packet.class);
//        packetService.savePacket(packet);
        logger.info(String.format("$$ -> Consumed Message -> %s",packet));
    }
}
