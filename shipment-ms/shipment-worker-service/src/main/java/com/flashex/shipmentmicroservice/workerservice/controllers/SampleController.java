package com.flashex.shipmentmicroservice.workerservice.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;

<<<<<<< HEAD
import com.flashex.shipmentmicroservice.lib.model.Packet;
import com.flashex.shipmentmicroservice.lib.model.Shipment;
import com.flashex.shipmentmicroservice.workerservice.messaging.Producer;
=======
import com.flashex.shipmentmicroservice.lib.model.Shipment;
import com.flashex.shipmentmicroservice.workerservice.messagingservice.ProducerService;
>>>>>>> c12f1fe414164602e3e786c40ec993732e00bbc5
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/kafka")
public class SampleController {
    private final ProducerService producer;

    @Autowired
    public SampleController(ProducerService producer) {
        this.producer = producer;
    }

    @PostMapping(value = "/publishJSON")
    public void sendMessageToKafkaTopicJSON(@RequestBody Shipment message) throws JsonProcessingException {
        this.producer.sendMessage(message);
    }
}
