package com.flashex.ordercollector.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.flashex.ordercollector.messaging.Producer;
import com.flashex.shipmentmicroservice.lib.model.Packet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {
    private final Producer producer;

    @Autowired
    public SampleController(Producer producer) {
        this.producer = producer;
    }

    @PostMapping(value = "/publishJSON")
    public Packet sendMessageToKafkaTopicJSON(@RequestBody Packet message) throws JsonProcessingException {
        this.producer.sendMessage(message);
        return message;
    }
}
