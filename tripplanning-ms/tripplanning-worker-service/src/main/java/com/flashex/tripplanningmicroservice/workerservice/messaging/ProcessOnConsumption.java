package com.flashex.tripplanningmicroservice.workerservice.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flashex.tripplanningmicroservice.lib.model.Shipment;
import com.flashex.tripplanningmicroservice.lib.services.ORService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProcessOnConsumption {

    private final ORService orService;
    private final Logger logger = LoggerFactory.getLogger(Consumer.class);

    private final Producer producer;

    public ProcessOnConsumption(ORService orService, Producer producer) {
        this.orService = orService;
        this.producer = producer;
    }

    public void processData(String message) throws JsonProcessingException {
        logger.info(String.format("$$ -> Consumed Message -> %s",message));
        Shipment shipmentReceived = new ObjectMapper().readValue(message, Shipment.class);
        String[] deliveryAddresses = shipmentReceived.getAllDeliveryAddresses();
        deliveryAddresses[0] = "hello";
        orService.settingAddressArray(deliveryAddresses);

        // plan the trip
        // some algo
        // store in db
//        producer.sendMessageJSON();
        //
    }
}
