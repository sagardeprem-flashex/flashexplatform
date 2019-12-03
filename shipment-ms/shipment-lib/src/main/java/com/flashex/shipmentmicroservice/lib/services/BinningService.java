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


    private static List<Bin> bins = new ArrayList<>();

    public static List<Bin> getBins() {
        return bins;
    }

    public static void setBins(List<Bin> bins) {
        BinningService.bins = bins;
    }


    public void binPacket(Packet packet){

        // get binning properties
        List<String> packetProperties = getPacketProperties(packet);
        System.out.println(packetProperties.toString());

        // find which bin to add
        int binIndex = this.getBinIndex(packetProperties);

        // add to bin
        bins.get(binIndex).getBinnedPackets().add(packet);

    }

    public List<Shipment> generateShipment(){
        List<Shipment> shipments = new ArrayList<>();

        if(bins.size()!=0){
//            bins.forEach(bin -> {
//                // generate shipments
//                List<List<Packet>> generatedPacketLists = ListUtils.partition(bin.getBinnedPackets(),getConfig().getMaxShipmentSize());
//                System.out.println("#########################Generating shipments################################");
//                System.out.println(generatedPacketLists.toString());
//                System.out.println("########################################################");
//                generatedPacketLists.forEach(generatedPacketList ->{
//                    Shipment shipment = new Shipment();
//
//                    // sorting to be done
//                    generatedPacketList=sortPacketList(generatedPacketList, getConfig().getSortBy());
//
//                    shipment.setPacketList((ArrayList<Packet>) generatedPacketList);
//                    shipment.setShipmentDate(new Date());
//                    shipment.setShipmentId(UUID.randomUUID().toString());
//                    shipments.add(shipment);
//                });
//
//                // clear bin on generating the shipment
//                bin.getBinnedPackets().clear();
//
//            });

            for(int i=0; i<bins.size();i++){
                // generate shipments
                List<List<Packet>> generatedPacketLists = ListUtils.partition(bins.get(i).getBinnedPackets(),getConfig().getMaxShipmentSize());
                System.out.println("#########################Generating shipments################################");
                System.out.println(generatedPacketLists.toString());
                System.out.println("########################################################");
//                generatedPacketLists.forEach(generatedPacketList ->{
//                    Shipment shipment = new Shipment();
//
//                    // sorting to be done
//                    generatedPacketList=sortPacketList(generatedPacketList, getConfig().getSortBy());
//
//                    shipment.setPacketList((ArrayList<Packet>) generatedPacketList);
//                    shipment.setShipmentDate(new Date());
//                    shipment.setShipmentId(UUID.randomUUID().toString());
//                    shipments.add(shipment);
//                });


                for(int j = 0; j<generatedPacketLists.size(); j++){
                    Shipment shipment = new Shipment();

                    shipment.setPacketList(sortPacketList(generatedPacketLists.get(j), getConfig().getSortBy()));
                    shipment.setShipmentDate(new Date());
                    shipment.setShipmentId(UUID.randomUUID().toString());
                    shipments.add(shipment);
                }


                // clear bin on generating the shipment
                bins.get(i).getBinnedPackets().clear();
            }
        }
        return shipments;
    }

    public void createBin(List<String> binningStrategy, String sortingStrategy){
        Bin bin = new Bin();
        bin.setBinningStrategy(binningStrategy);
        bin.setSortingStrategy(sortingStrategy);
        bin.setCreatedOn(new Date());
        bin.setBinnedPackets(new ArrayList<>());
        bins.add(bin);
    }


    public List<String> getPacketProperties(Packet packet){
        List<String> groupBy = getConfig().getGroupStrategy();
        List<String> packetProperty = new ArrayList<>();

        for(String groupByField: groupBy){
            if(groupByField=="PINCODE"){
                packetProperty.add(Long.toString(packet.getDeliveryAddress().getPincode()));
//                packetProperty.add(Integer.toString(packet.getDeliveryAddress().getPincode()));
            } else if(groupByField=="PACKET_TYPE"){
                packetProperty.add(packet.getPacketType());
            }
        }
        return packetProperty;
    }


    public int getBinIndex(List<String> packetProperties){
        System.out.println(">>>>>>>>>>>>>>>Getting bin index >>>>>>>>>>>");

        int binIndex = 0;
        if(bins.size()>0){
            for (int i=0; i < bins.size();i++){
                if(bins.get(i).getBinningStrategy().equals(packetProperties) ){
                    binIndex=i;
                } else {
                    createBin(packetProperties, getConfig().getSortBy());
                    binIndex=bins.size()-1;
                }
            }
        }else {
            createBin(packetProperties, getConfig().getSortBy());
        }

        System.out.println(">>>>>>>>>>>>>>>Getting bin index done >>>>>>>>>>>"+binIndex);
       return binIndex;
    }

    public List<Packet> sortPacketList(List<Packet> packetList,String sortBy){

        List<Packet> sortedPackets = packetList;
        if(sortBy=="RECEIVED_DATE"){
            sortedPackets.sort(Comparator.comparing(Packet::getReceivedDate));
        }
        return sortedPackets;
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
