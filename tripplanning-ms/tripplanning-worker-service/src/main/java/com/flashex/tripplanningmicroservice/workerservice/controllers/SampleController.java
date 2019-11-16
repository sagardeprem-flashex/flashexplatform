package com.flashex.tripplanningmicroservice.workerservice.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.flashex.tripplanningmicroservice.lib.model.TestClass;
import com.flashex.tripplanningmicroservice.workerservice.messaging.Producer;
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
    public void sendMessageToKafkaTopicJSON(@RequestBody TestClass message) throws JsonProcessingException {
        this.producer.sendMessageJSON(message);
    }
}
