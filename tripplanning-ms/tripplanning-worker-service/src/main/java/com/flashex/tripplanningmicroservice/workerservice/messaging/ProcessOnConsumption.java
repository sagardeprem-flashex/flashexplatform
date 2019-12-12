package com.flashex.tripplanningmicroservice.workerservice.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flashex.tripplanningmicroservice.lib.getjsonserver.GetJsonServerData;
import com.flashex.tripplanningmicroservice.lib.model.Shipment;
import com.flashex.tripplanningmicroservice.lib.model.TripItinerary;
import com.flashex.tripplanningmicroservice.lib.model.Vehicle;
import com.flashex.tripplanningmicroservice.lib.model.VehicleList;
import com.flashex.tripplanningmicroservice.lib.services.ORService;
import com.flashex.tripplanningmicroservice.lib.services.ProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class ProcessOnConsumption {

    private final ORService orService;
    private final Logger logger = LoggerFactory.getLogger(Consumer.class);

    @Autowired
    ProducerService producerService;

    public ProcessOnConsumption(ORService orService) {
        this.orService = orService;
    }

    public void processData(String message) throws Exception {
//        logger.info(String.format("$$ -> Consumed Message -> %s",message));
        Shipment shipmentReceived = new ObjectMapper().readValue(message, Shipment.class);
//        logger.info("shipmentRecieved -------------------------> "+ shipmentReceived.getPacketList());
//        String[] deliveryAddresses = shipmentReceived.getAllDeliveryAddresses();
//        logger.info("deliveryAddresses -------------------------> "+deliveryAddresses);
        orService.settingShipment(shipmentReceived);

        GetJsonServerData getJsonServerData = new GetJsonServerData();
        List<Vehicle> vehicles = getJsonServerData.processJsonData();

        logger.info("Vehicles List ---------------> "+vehicles);

        VehicleList vehicleList = new VehicleList(vehicles);

        logger.info("VehicleList ---------------> "+vehicleList);

        orService.settingVehicleDetails(vehicleList);

        logger.info("<<<<<<<<<<<<<<<<<<<<<<Method:1 starts here>>>>>>>>>>>>>>>>>>");
        List<TripItinerary> capConstraintTrips = orService.VrpfunctionWithCapCons(shipmentReceived.getPacketList());
        logger.info("<<<<<<<<<<<<<<<<<<<<<<Method:1 ends here>>>>>>>>>>>>>>>>>>>>>");

        logger.info("<<<<<<<<<<<<<<<<<<<<<<Method:2 starts here>>>>>>>>>>>>>>>>>>>>");
        List<TripItinerary> dropNodesTrips = orService.VrpfuncWithDropNode(shipmentReceived.getPacketList(), 50000);
        logger.info("<<<<<<<<<<<<<<<<<<<<<<Method:2 ends here>>>>>>>>>>>>>>>>>>>>>>>");

        logger.info("<<<<<<<<<<<<<<<<<<<<<<Method:3 starts here>>>>>>>>>>>>>>>>>>>>>>");
        List<TripItinerary> timeWindowTrips = orService.TimeWindowConsFunction(shipmentReceived.getPacketList(), 50);
        logger.info("<<<<<<<<<<<<<<<<<<<<<<Method:3 ends here>>>>>>>>>>>>>>>>>>>>>>>>>");

        for(int i=0; i<capConstraintTrips.size(); i++) {
            producerService.sendMessageJSON(capConstraintTrips.get(i));
        }
    }

}
