package com.flashex.shipmentmicroservice.lib.services;

import com.flashex.shipmentmicroservice.lib.model.Bin;
import com.flashex.shipmentmicroservice.lib.model.BinnerConfig;
import com.flashex.shipmentmicroservice.lib.model.Packet;
import com.flashex.shipmentmicroservice.lib.model.Shipment;
import org.apache.commons.collections4.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BinningService {


    private  static final Logger logger = (Logger) LoggerFactory.getLogger(BinningService.class);

    // Local list of bins
    private static List<Bin> bins = new ArrayList<>();

    public static List<Bin> getBins() {
        return bins;
    }

    public static void setBins(List<Bin> bins) {
        BinningService.bins = bins;
    }


    // adds a packet to a bin
    public void binPacket(Packet packet){

        // get binning properties to decide which bin to add to
        List<String> packetProperties = getPacketProperties(packet);
        System.out.println(packetProperties.toString());

        // find bin based on properties
        int binIndex = this.getBinIndex(packetProperties);

        // add to bin
        bins.get(binIndex).getBinnedPackets().add(packet);

    }

    // generates shipments from all bins
    public List<Shipment> generateShipment(){

        // list of all shipments
        List<Shipment> shipments = new ArrayList<>();

        if(bins.size()!=0){

            for(int i=0; i<bins.size();i++){

                // create sublist for each bin for adding to shipments
                List<List<Packet>> generatedPacketLists = ListUtils.partition(bins.get(i).getBinnedPackets(),getConfig().getMaxShipmentSize());

                // for each sublist, create a shipment
                for(int j = 0; j<generatedPacketLists.size(); j++){
                    Shipment shipment = new Shipment();
                    shipment.setPacketList(sortPacketList(generatedPacketLists.get(j), getConfig().getSortBy()));
                    shipment.setShipmentDate(new Date());
                    shipment.setShipmentId(UUID.randomUUID().toString());
                    shipments.add(shipment);
                }
            }

            // clear bin on generating the shipment
            bins.forEach(bin -> {
                bin.setBinnedPackets(new ArrayList<>());
            });
        }
        return shipments;
    }

    // create a bin based on a certain grouping/binning strategy and sorting strategy
    public void createBin(List<String> binningStrategy, String sortingStrategy){
        Bin bin = new Bin();
        bin.setBinningStrategy(binningStrategy);
        bin.setSortingStrategy(sortingStrategy);
        bin.setCreatedOn(new Date());
        bin.setBinnedPackets(new ArrayList<>());
        bins.add(bin);
    }

    // gets properties from a packet to decide which bin it will go to
    public List<String> getPacketProperties(Packet packet){

        List<String> groupBy = getConfig().getGroupStrategy();
        List<String> packetProperty = new ArrayList<>();

        for(String groupByField: groupBy){
            if(groupByField=="PINCODE"){
                packetProperty.add(Long.toString(packet.getDeliveryAddress().getPincode()));
            } else if(groupByField=="PACKET_TYPE"){
                packetProperty.add(packet.getPacketType());
            }
        }
        return packetProperty;
    }


    public int getBinIndex(List<String> packetProperties){
        logger.debug("Getting the bin index......");
        int binIndex = 0;
        if(bins.size()>0){
            // if there are bins already, go ahead
            for (int i=0; i < bins.size();i++){
                // check if a bin exists for the given properties
                if(bins.get(i).getBinningStrategy().equals(packetProperties) ){
                    binIndex=i;
                } else {
                    // else create a bin
                    createBin(packetProperties, getConfig().getSortBy());
                    binIndex=bins.size()-1;
                }
            }
        }else {
            // if bins is empty, create a bin with given properties
            createBin(packetProperties, getConfig().getSortBy());
        }
        logger.debug("Bin index: {}", binIndex);
       return binIndex;
    }

    // sorts a list of packets with the given strategy
    public List<Packet> sortPacketList(List<Packet> packetList,String sortBy){

        List<Packet> sortedPackets = packetList;
        if(sortBy=="RECEIVED_DATE"){
            sortedPackets.sort(Comparator.comparing(Packet::getReceivedDate));
        }
        // more to come
        return sortedPackets;
    }


    // gives the config to create bins, hard coded for now
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
