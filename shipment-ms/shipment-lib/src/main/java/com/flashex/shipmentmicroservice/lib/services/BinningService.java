package com.flashex.shipmentmicroservice.lib.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.flashex.shipmentmicroservice.lib.model.*;
import org.apache.commons.collections4.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BinningService {


    @Autowired
    ShipmentService shipmentService;


    // used for sending messages
    @Autowired
    MessagingService producerService;

    private  static final Logger logger = (Logger) LoggerFactory.getLogger(BinningService.class);

    // Local list of bins
    private static List<Bin> bins = new ArrayList<>();

    public List<Bin> getBins() {
        return bins;
    }

    public void setBins(List<Bin> bins) {
        BinningService.bins = bins;
    }

    // adds a packet to a bin
    public void binPacket(Packet packet){

        // show all bins
        logger.info("$$ Current size of bins ----------->: {}", bins.size());

        // get binning properties to decide which bin to add to
        logger.info("$$ Retrieving properties to decide bin----------->");
        List<String> packetProperties = getPacketProperties(packet);
        logger.info(" $$ Extracted properties-----------> {}",packetProperties.toString());

        // find bin based on properties
        int binIndex = this.indexOfBin(packetProperties);
        logger.info("Packet being added to bin number: {}", binIndex);

        // add to bin
        bins.get(binIndex).getBinnedPackets().add(packet);

    }


    public void generateFixedShipments() throws JsonProcessingException {

        logger.info("$$ Shipement generation triggered ----------->");

        // storing the number of packets shipped, needed for clean up
        List<Integer> binPacketsSize = new ArrayList<>();

        if (bins.size()!=0){

            // loop through bins to generate shipment
            for(int i=0; i<bins.size();i++){

                int binSize=bins.get(i).getBinnedPackets().size();
                logger.info("$$ Size of bin {} before shipment generation and cleaning ----------->: {}", i, binSize);

                // number of shipments that can be generated
                int nShipments = (int) Math.floor(binSize/getConfig().getMaxShipmentSize());

                // total packets in nShipments
                int nPackets = getConfig().getMaxShipmentSize()*nShipments;
                binPacketsSize.add(nPackets);

                logger.info("$$ A total of {} packets can be shipped now ----------->", nPackets);
                logger.debug("$$ Showing packets that can be shipped now  ----------->  {}", bins.get(i).getBinnedPackets().subList(0,nPackets));
                // pick only those packets which make up the exact shipment size
                List<List<Packet>> generatedPacketLists = ListUtils.partition(bins.get(i).getBinnedPackets().subList(0,nPackets),getConfig().getMaxShipmentSize());

                // print the sublists
                generatedPacketLists.forEach(packetList -> {
                    logger.debug("Packet list ------------- {}", packetList);
                });

                logger.info("$$ Number of shipments being generated----------->: {}", generatedPacketLists.size());
                logger.info("$$ This must be equal to {} ", nShipments);

                // generate shipments from the packet lists
                for(int j = 0; j<generatedPacketLists.size(); j++){

                    // create a new shipment from the packet list
                    Shipment shipment = new Shipment();
                    shipment.setPacketList(sortPacketList(generatedPacketLists.get(j), getConfig().getSortBy()));
                    shipment.setShipmentDate(new Date());
                    shipment.setShipmentId(UUID.randomUUID().toString());
                    shipment.setOriginAddress(getConfig().getOriginAddress());

                    logger.info("Number of packets in this shipment is ----------->: {} ", shipment.getPacketList().size());

                    producerService.sendShipment(shipment);
                    updatePacketStatus(shipment.getPacketList());
                    shipmentService.saveShipments(Collections.singletonList(shipment));
                }
                logger.info("$$ Bin {} with properties {} processed successfully ----------->",i, bins.get(i).getBinningStrategy());

            }

        }

        // clean up
        if(bins.size()>0 && binPacketsSize.size() >0 ){
            for(int j=0; j<bins.size();j++){

                logger.info("Initiating clean up for bin ----------->{}", j);
                logger.info("Size of bin {} before cleaning: {} ", j, bins.get(j).getBinnedPackets().size());
                bins.get(j).getBinnedPackets().subList(0,binPacketsSize.get(j)).clear();
                logger.info("Size of bin {} after clean up: {}",j, bins.get(j).getBinnedPackets().size());
            }
        }

    }

    public void updatePacketStatus(List<Packet> packets){
        packets.forEach(packet -> {
            KafkaStatusMessage message = new KafkaStatusMessage();
            message.setPacketId(packet.getPacketId());
            message.setTimeStamp(new Date());
            message.setStatusValue("PROCESSING");
            try {
                producerService.sendUpdates(message);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
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


    // get Bin index
    public int indexOfBin(List<String> packetProperties){
        logger.info("$$ Checking if a bin exists for this packet ----------->");
        int binIndex =0;

        if(this.bins.size()>0){
            // if bins already exist, search for bin

            // assume no inital match
            Boolean isMatched = false;
            for(int i=0; i<this.bins.size(); i++){
                // iteratively loop to find the bin
                if(bins.get(i).getBinningStrategy().equals(packetProperties)){
                    //if found, set this to true
                    isMatched = true;
                    binIndex = i;
                    break;
                }
            }
            // if match was not found, create bin
            if(isMatched == false){
                createBin(packetProperties, getConfig().getSortBy());
                binIndex = bins.size()-1;
            }

        }else {
            // no bins yet, create one
            createBin(packetProperties, getConfig().getSortBy());
        }

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
        DeliveryAddress origin = new DeliveryAddress();
        origin.setAddressLine1("13610+Hacks+Cross+Rd+Memphis+TN");
        origin.setCity("Bengaluru");
        origin.setState("Karnataka");
        origin.setLongitude(77.6132100821);
        origin.setLatitude(12.9207427973);
        origin.setPincode(560096);
        config.setOriginAddress(origin);
        return config;
    }

}
