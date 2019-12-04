package com.flashex.tripplanningmicroservice.workerservice.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flashex.tripplanningmicroservice.lib.ORTools.TimeWindowDelivery;
import com.flashex.tripplanningmicroservice.lib.ORTools.genmatrix.Urllib;
import com.flashex.tripplanningmicroservice.lib.model.Shipment;
import com.flashex.tripplanningmicroservice.lib.model.Vehicle;
import com.flashex.tripplanningmicroservice.lib.model.VehicleList;
import com.flashex.tripplanningmicroservice.lib.services.ORService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.Arrays;

@RestController
public class ProcessOnConsumption {

    private final ORService orService;
    private final Logger logger = LoggerFactory.getLogger(Consumer.class);

    private final Producer producer;

    public ProcessOnConsumption(ORService orService, Producer producer) {
        this.orService = orService;
        this.producer = producer;
    }

    public void processData(String message) throws Exception {
//        logger.info(String.format("$$ -> Consumed Message -> %s",message));
        Shipment shipmentReceived = new ObjectMapper().readValue(message, Shipment.class);
//        logger.info("6666666666666666666666666666666666666666666666666666666[[[[[[[[[[[[[[[[[[[[]]]]]]]]]]]]]]"+String.valueOf(shipmentReceived.getPacketList()));
        String[] deliveryAddresses = shipmentReceived.getAllDeliveryAddresses();
//        logger.info("------->>>>>>>>"+ Arrays.toString(deliveryAddresses));
//        deliveryAddresses[0] = "117+Above+SBI+Opposite+Raheja+Arcade+7th+Block+Koramangala+Bengaluru+Karnataka+560095";
        deliveryAddresses[0] = "13610+Hacks+Cross+Rd+Memphis+TN";
//        logger.info("<<<<<<<<<<<<<<---------------->>>>>>>>"+ Arrays.toString(deliveryAddresses));
        orService.settingAddressArray(deliveryAddresses);

        orService.settingPacketList(shipmentReceived.getPacketList());
//        logger.info("<<<<<<<<<<<<<<<<<<<<<<Method:1 starts here>>>>>>>>>>>>>>>>>>");
//        orService.VrpfunctionWithCapCons();
//        logger.info("<<<<<<<<<<<<<<<<<<<<<<Method:1 ends here>>>>>>>>>>>>>>>>>>>>>");
//
//        logger.info("<<<<<<<<<<<<<<<<<<<<<<Method:2 starts here>>>>>>>>>>>>>>>>>>>>");
//        orService.VrpfuncWithDropNode();
//        logger.info("<<<<<<<<<<<<<<<<<<<<<<Method:2 ends here>>>>>>>>>>>>>>>>>>>>>>>");

        logger.info("<<<<<<<<<<<<<<<<<<<<<<Method:3 starts here>>>>>>>>>>>>>>>>>>>>>>");
        orService.TimeWindowConsFunction();
        logger.info("<<<<<<<<<<<<<<<<<<<<<<Method:3 ends here>>>>>>>>>>>>>>>>>>>>>>>>>");




    }

}
