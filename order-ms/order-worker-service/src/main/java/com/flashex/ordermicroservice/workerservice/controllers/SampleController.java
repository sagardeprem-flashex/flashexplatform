package com.flashex.ordermicroservice.workerservice.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.flashex.ordermicroservice.lib.model.TestClass;
import com.flashex.ordermicroservice.workerservice.messaging.Producer;

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

    @PostMapping(value = "/publishJSON")
    public void sendMessageToKafkaTopicJSON(@RequestBody TestClass message) throws JsonProcessingException {
        this.producer.sendMessage(message);
    }
}
