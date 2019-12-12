package com.flashex.shipmentmicroservice.workerservice.cotrollers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.flashex.shipmentmicroservice.lib.model.Shipment;
import com.flashex.shipmentmicroservice.workerservice.messagingservice.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaTester {


    private final ProducerService producer;

    @Autowired
    public KafkaTester (ProducerService producer) {
        this.producer = producer;
    }

    @PostMapping(value = "/publishString")
    public void sendMessageToKafkaTopic(@RequestParam("message") String message){
        this.producer.sendMessage(message);
    }

    @PostMapping(value = "/publishJSON")
    public void sendMessageToKafkaTopicJSON(@RequestBody Shipment message) throws JsonProcessingException {
        this.producer.sendMessageJSON(message);

    }

}
