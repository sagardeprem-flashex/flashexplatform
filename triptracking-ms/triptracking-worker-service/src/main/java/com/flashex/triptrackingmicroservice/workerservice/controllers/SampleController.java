package com.flashex.triptrackingmicroservice.workerservice.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.flashex.triptrackingmicroservice.lib.model.TripsDetails;
import com.flashex.triptrackingmicroservice.workerservice.messaging.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/kafka")
public class SampleController {
    private final Producer producer;

    @Autowired
    public SampleController(Producer producer) {
        this.producer = producer;
    }

    @PostMapping(value = "/publishString")
    public void sendMessageToKafkaTopic(@RequestParam("message") String message){
        this.producer.sendMessage(message);
    }

    @PostMapping(value = "/publishJSON")
    public void sendMessageToKafkaTopicJSON(@RequestBody TripsDetails message) throws JsonProcessingException {
        this.producer.sendMessageJSON(message);
    }
}
