package com.flashex.shipmentmicroservice.lib.services;

import com.flashex.shipmentmicroservice.lib.model.Shipment;
import com.flashex.shipmentmicroservice.lib.repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ShipmentService {

    @Autowired
    private ShipmentRepository shipmentRepository;

    public List<Shipment> getAllShipments(){
        return shipmentRepository.findAll();
    }

    public ArrayList<Shipment> saveShipments(ArrayList<Shipment> shipments){
        ArrayList<Shipment> savedShipments = new ArrayList<>();
        for (Shipment shipment : shipments) {
            shipment.setShipmentId(UUID.randomUUID().toString());
            savedShipments.add(this.shipmentRepository.save(shipment));
        }
        return savedShipments;
    }
}
