package com.flashex.tripplanningmicroservice.workerservice.messaging;

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

    public ProcessOnConsumption(ORService orService) {
        this.orService = orService;
    }

    public void processData(String message) throws Exception {
//        logger.info(String.format("$$ -> Consumed Message -> %s",message));
        Shipment shipmentReceived = new ObjectMapper().readValue(message, Shipment.class);
        String[] deliveryAddresses = shipmentReceived.getAllDeliveryAddresses();
        orService.settingAddressArray(deliveryAddresses);

        logger.info("<<<<<<<<<<<<<<<<<<<<<<Method:1 starts here>>>>>>>>>>>>>>>>>>");
        orService.VrpfunctionWithCapCons(shipmentReceived.getPacketList());
        logger.info("<<<<<<<<<<<<<<<<<<<<<<Method:1 ends here>>>>>>>>>>>>>>>>>>>>>");

        logger.info("<<<<<<<<<<<<<<<<<<<<<<Method:2 starts here>>>>>>>>>>>>>>>>>>>>");
        orService.VrpfuncWithDropNode(shipmentReceived.getPacketList());
        logger.info("<<<<<<<<<<<<<<<<<<<<<<Method:2 ends here>>>>>>>>>>>>>>>>>>>>>>>");

        logger.info("<<<<<<<<<<<<<<<<<<<<<<Method:3 starts here>>>>>>>>>>>>>>>>>>>>>>");
        orService.TimeWindowConsFunction(shipmentReceived.getPacketList());
        logger.info("<<<<<<<<<<<<<<<<<<<<<<Method:3 ends here>>>>>>>>>>>>>>>>>>>>>>>>>");




    }

}
