package com.flashex.shipmentmicroservice.lib.services;

import com.flashex.shipmentmicroservice.lib.model.ShipmentGenerationConfig;
import com.flashex.shipmentmicroservice.lib.repository.ShipmentGenerationConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipmentGenerationConfigService {

    @Autowired
    private ShipmentGenerationConfigRepository shipmentGenerationConfigRepository;

    public ShipmentGenerationConfig setShipmentGenerationConfig(ShipmentGenerationConfig shipmentGenerationConfig){

        // change active status of current config
//        if(this.getCurrentConfig() != null){
//            ShipmentGenerationConfig oldConfig = this.getCurrentConfig();
//            oldConfig.setActive(false);
//            shipmentGenerationConfigRepository.save(oldConfig);
//        }

        // save new config
        return this.shipmentGenerationConfigRepository.save(shipmentGenerationConfig);
    }

    public ShipmentGenerationConfig getCurrentConfig(){
        return this.shipmentGenerationConfigRepository.findByActiveContaining("TRUE").get();
    }

    public List<ShipmentGenerationConfig> getAllConfigLog(){
        return this.shipmentGenerationConfigRepository.findAll();
    }
}
