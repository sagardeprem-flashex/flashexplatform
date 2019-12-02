package com.flashex.shipmentmicroservice.workerservice.scheduledtasks;

import com.flashex.shipmentmicroservice.lib.model.Shipment;
import com.flashex.shipmentmicroservice.lib.model.ShipmentGenerationConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class ShipmentGeneration {
    private static final Logger log = LoggerFactory.getLogger(ShipmentGeneration.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(cron = "1 * * * * ?")
    public Shipment generateShipment() {
        log.info("The time is now {}", dateFormat.format(new Date()));


        return null;

    }

    public ShipmentGenerationConfig getConfig(){
        ShipmentGenerationConfig config = new ShipmentGenerationConfig();
        config.setActive("TRUE");
        config.setConfigDate(new Date());
        config.setConfigId(UUID.randomUUID().toString());
        config.setEffectiveDate(new Date());
        config.setSortBy("receivedDate");
        List<String> groupStrategy = new ArrayList<>();
        groupStrategy.add("pincode");
        groupStrategy.add("packetType");
        config.setGroupStrategy(groupStrategy);
        return config;
    }
}
