package com.flashex.shipmentmicroservice.webservice.controller;

import com.flashex.shipmentmicroservice.lib.model.ShipmentGenerationConfig;
import com.flashex.shipmentmicroservice.lib.services.ShipmentGenerationConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ShipmentGenerationConfigController {

    @Autowired
    private ShipmentGenerationConfigService shipmentGenerationConfigService;

    @GetMapping("/shipment-gen-config")
    public List<ShipmentGenerationConfig> getCurrentConfig(){
            return this.shipmentGenerationConfigService.getAllConfigLog();
    }

    @PostMapping("/shipment-gen-config")
    public ShipmentGenerationConfig setCurrentConfig(@RequestBody ShipmentGenerationConfig shipmentGenerationConfig){
        return this.shipmentGenerationConfigService.setShipmentGenerationConfig(shipmentGenerationConfig);
    }
}
