package com.flashex.tripplanningmicroservice.lib.model.bingdm;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.flashex.tripplanningmicroservice.lib.ORTools.bingsupport.services.BingServices;
import com.flashex.tripplanningmicroservice.lib.model.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;


@Getter
@Setter
public class DataModel {

    static VehicleList[] vehicleList = new VehicleList[3];

    Shipment shipment;
    int depot;
    long[][] distanceMatrix;
    long[][] timeMatrix;

    public DataModel(Shipment shipment, int depot) throws JsonProcessingException {
        this.shipment = shipment;
        this.depot = depot;
//        this.distanceMatrix = generateDistanceMatrix();
//        this.timeMatrix = generateTimeMatrix();
        this.distanceMatrix = generateDistanceMatrix(shipment);
        this.timeMatrix = generateTimeMatrix(shipment);
    }

    public float[] getDemands() {
        float[] demands = new float[shipment.getPacketList().size()];
        for(int i=0; i<shipment.getPacketList().size(); i++) {
            Packet packet = shipment.getPacketList().get(i);
            demands[i] = packet.getHeight() * packet.getBreadth() * packet.getLength();
        }
        return demands;
    }

    public String[] getAddresses() {
        String[] addresses = new String[shipment.getPacketList().size()];
        for(int i=0; i<shipment.getPacketList().size(); i++){
            Packet packet = shipment.getPacketList().get(i);
            addresses[i] = packet.getDeliveryAddress().getAddressLine1();
        }
        return addresses;
    }

    public long[] getVehicleCapacity(int algoIndex) {
        long[] capacities = new long[vehicleList[algoIndex].getListofvehicle().size()];
        for(int i=0; i<vehicleList[algoIndex].getListofvehicle().size(); i++){
            Vehicle vehicle = vehicleList[algoIndex].getListofvehicle().get(i);
            capacities[i] = vehicle.getVehicleVolume();
        }
        return capacities;
    }

    public long[][] generateDistanceMatrix (Shipment shipment) throws JsonProcessingException {
        List<DeliveryAddress> origins = new ArrayList<>();
        List<DeliveryAddress> destinations = new ArrayList<>();
        origins.add(shipment.getOriginAddress());
        destinations.add(shipment.getOriginAddress());
        for(int i=0;i<shipment.getPacketList().size(); i++) {
            origins.add(shipment.getPacketList().get(i).getDeliveryAddress());
            destinations.add(shipment.getPacketList().get(i).getDeliveryAddress());
        }
        Logger.getLogger(DataModel.class.getName()).info("Origin and Destination addresses --------------------> " + origins + "-------"+ destinations);
        long[][] distMat =  BingServices.requestDistanceMatrix(origins,destinations);
        Logger.getLogger(DataModel.class.getName()).info("DistanceMatrix --------------------> " + Arrays.deepToString(distMat));
        return distMat;
    }

    public long[][] generateTimeMatrix (Shipment shipment) throws JsonProcessingException {
        List<DeliveryAddress> origins = new ArrayList<>();
        List<DeliveryAddress> destinations = new ArrayList<>();
        origins.add( shipment.getOriginAddress());
        destinations.add(shipment.getOriginAddress());
        for(int i=0;i<shipment.getPacketList().size(); i++) {
            origins.add(shipment.getPacketList().get(i).getDeliveryAddress());
            destinations.add(shipment.getPacketList().get(i).getDeliveryAddress());
        }
        return BingServices.requestTimeMatrix(origins,destinations);
    }

    public static void setVehicleList(VehicleList newVehicleList, int algoIndex) {
        vehicleList[algoIndex] = newVehicleList;
    }

    public static VehicleList getVehicleList(int algoIndex) {
        return vehicleList[algoIndex];
    }

}
