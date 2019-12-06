package com.flashex.shipmentmicroservice.workerservice.schedules;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.flashex.shipmentmicroservice.lib.model.Shipment;
import com.flashex.shipmentmicroservice.lib.services.BinningService;
import com.flashex.shipmentmicroservice.workerservice.messagingservice.ProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class ShipmentGeneration {
    private static final Logger log = LoggerFactory.getLogger(ShipmentGeneration.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    BinningService binningService;

    @Autowired
    ProducerService producer;

//    Uncommenting until JSON stream doesn't work
    @Scheduled(cron = "2 * * * * ?")
    public void generateShipment() {
        log.info("Sending shipments {}", dateFormat.format(new Date()));

//        List<Shipment> shipments = binningService.generateShipment();
        List<Shipment> shipments = binningService.generateFixedShipments();

        System.out.println(shipments.toString());

        if(shipments.size()>0){
            for (int i=0; i<shipments.size(); i++){
                try {
                    producer.sendMessage(shipments.get(i));
                    System.out.println(shipments.get(i).toString());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }else {
            System.out.println("No shipment generated");
        }

    }

}
