package com.flashex.shipmentmicroservice.lib.services;

import com.flashex.shipmentmicroservice.lib.model.Bin;
import com.flashex.shipmentmicroservice.lib.model.BinnerConfig;
import com.flashex.shipmentmicroservice.lib.model.Packet;
import com.flashex.shipmentmicroservice.lib.model.Shipment;
import org.apache.commons.collections4.ListUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BinningService {


    private static List<Bin> bins;

    public static List<Bin> getBins() {
        return bins;
    }

    public static void setBins(List<Bin> bins) {
        BinningService.bins = bins;
    }


    public void binPacket(Packet packet){

        // get binning properties
        List<String> packetProperties = getPacketProperties(packet);

        // find which bin to add
        int binIndex = getBinIndex(packetProperties);

        // add to bin
        bins.get(binIndex).getBinnedPackets().add(packet);

    }

    public List<Shipment> generateShipment(){
        List<Shipment> shipments = new ArrayList<>();

        if(bins.size()!=0){
            bins.forEach(bin -> {
                // generate shipments
                List<List<Packet>> generatedShipments = ListUtils.partition(bin.getBinnedPackets(),getConfig().getMaxShipmentSize());
                generatedShipments.forEach(generatedShipment ->{
                    Shipment shipment = new Shipment();

                    // sorting to be done 
//                    generatedShipment.sort();
                    shipment.setPacketList((ArrayList<Packet>) generatedShipment);
                    shipment.setShipmentDate(new Date());
                    shipment.setShipmentId(UUID.randomUUID().toString());
                    shipments.add(shipment);
                });

                // clear bin on generating the shipment
                bin.getBinnedPackets().clear();

            });
        }
        return shipments;
    }

    public void createBin(List<String> binningStrategy, String sortingStrategy){
        Bin bin = new Bin();
        bin.setBinningStrategy(binningStrategy);
        bin.setSortingStrategy(sortingStrategy);
        bin.setCreatedOn(new Date());
        bins.add(bin);
    }


    public List<String> getPacketProperties(Packet packet){
        List<String> groupBy = getConfig().getGroupStrategy();
        List<String> packetProperty = new ArrayList<>();

        for(String groupByField: groupBy){
            if(groupByField=="PINCODE"){
                packetProperty.add(Integer.toString(packet.getDeliveryAddress().getPincode()));
            } else if(groupByField=="PACKET_TYPE"){
                packetProperty.add(packet.getPacketType());
            }
        }
        return packetProperty;
    }


    public int getBinIndex(List<String> packetProperties){

        int binIndex = 0;
        for (int i=0; i < bins.size();i++){
            if(bins.get(i).getBinningStrategy().equals(packetProperties) ){
                binIndex=i;
            } else {
                createBin(packetProperties, getConfig().getSortBy());
                binIndex=bins.size()-1;
            }
        }
       return binIndex;
    }


    public BinnerConfig getConfig(){

        BinnerConfig config = new BinnerConfig();
        config.setConfigDate(new Date());
        config.setConfigId(UUID.randomUUID().toString());
        config.setSortBy("RECEIVED_DATE");
        List<String> groupStrategy = new ArrayList<>();
        groupStrategy.add("PINCODE");
        groupStrategy.add("PACKET_TYPE");
        config.setGroupStrategy(groupStrategy);
        config.setMaxShipmentSize(15);
        return config;
    }

}
