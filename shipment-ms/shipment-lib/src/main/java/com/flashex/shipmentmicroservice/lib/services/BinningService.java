package com.flashex.shipmentmicroservice.lib.services;

import com.datastax.driver.core.LoggingMonotonicTimestampGenerator;
import com.flashex.shipmentmicroservice.lib.model.Bin;
import com.flashex.shipmentmicroservice.lib.model.BinnerConfig;
import com.flashex.shipmentmicroservice.lib.model.Packet;
import com.flashex.shipmentmicroservice.lib.model.Shipment;
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

    private  static final Logger logger = (Logger) LoggerFactory.getLogger(BinningService.class);

    // Local list of bins
    private List<Bin> bins = new ArrayList<>();

    public List<Bin> getBins() {
        return bins;
    }

    public void setBins(List<Bin> bins) {
        this.bins = bins;
    }

    // adds a packet to a bin
    public void binPacket(Packet packet){

        // show all bins
        logger.info("Current size of bins: {}", bins.size());

        // get binning properties to decide which bin to add to
        logger.info("Retrieving properties to decide bin");
        List<String> packetProperties = getPacketProperties(packet);
        logger.info("Found bin properties>>>>>>>>>>>>>>>>>>");
        logger.info(packetProperties.toString());
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>");

        // find bin based on properties
        int binIndex = this.getBinIndex(packetProperties);
        logger.info("Binned to packet: {}", binIndex);

        // add to bin
        bins.get(binIndex).getBinnedPackets().add(packet);

    }


    public List<Shipment> generateFixedShipments(){
        List<Shipment> shipments = new ArrayList<>();
        if (bins.size()!=0){
            for(int i=0; i<bins.size();i++){

                int binSize=bins.get(i).getBinnedPackets().size();

                // number of shipments that can be generated
                int nShipments = (int) Math.floor(binSize/getConfig().getMaxShipmentSize());

                // total packets in nShipments
                int nPackets = getConfig().getMaxShipmentSize()*nShipments;


                // pick only those packets which make up the exact shipment size
                List<List<Packet>> generatedPacketLists = ListUtils.partition(bins.get(i).getBinnedPackets().subList(0,nPackets),getConfig().getMaxShipmentSize());


                // generate shipments from the packet lists
                for(int j = 0; j<generatedPacketLists.size(); j++){
                    Shipment shipment = new Shipment();
                    shipment.setPacketList(sortPacketList(generatedPacketLists.get(j), getConfig().getSortBy()));
                    shipment.setShipmentDate(new Date());
                    shipment.setShipmentId(UUID.randomUUID().toString());

                    logger.debug("%%%%%%%%%%%%%%%%%%%%%%%%Size of shipment is : {}%%%%%%%%%%%%%%%%  ", shipment.getPacketList().size());
                    shipments.add(shipment);
                }

                // clean up once shipment is generated
                Bin cleanedBin = bins.get(i);
                cleanedBin.getBinnedPackets().subList(0,nPackets).clear();
                logger.info("Size of bin {} before cleaning: {}", i, bins.get(i).getBinnedPackets().size());
                bins.set(i,cleanedBin);
                logger.info("Size of bin {} after clean up: {}",i, bins.get(i).getBinnedPackets().size());
                logger.info("Properties of bin {}: {}",i, bins.get(i).getBinningStrategy());
            }
        }
        return shipments;
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

        // save generated shipments to db
        if(shipments.size()>0){
            shipmentService.saveShipments(shipments);
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
        this.bins.add(bin);
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
\           }
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
//                if(bins.get(i).getBinningStrategy().equals(packetProperties) ){


                if( compareArrays(bins.get(i).getBinningStrategy(), packetProperties)){
                    logger.info("comparing binning properties : "+ bins.get(i).getBinningStrategy().get(0)+">>>>>>>to>>>>>>>"+packetProperties.get(0));
                    logger.info("comparing binning properties : "+ bins.get(i).getBinningStrategy().get(1)+">>>>>>>to>>>>>>>"+packetProperties.get(1));
                    binIndex=i;
                } else {
                    // else create a bin
                    createBin(packetProperties, getConfig().getSortBy());
                    binIndex=bins.size()-1;
                    logger.info("Bin not found, creating bin>>>>>>>>>>>>>");
                    logger.info("Required properties:{},{} ", packetProperties.get(0),packetProperties.get(1));
                    logger.info("Created properties: {},{}", bins.get(binIndex).getBinningStrategy().get(0),bins.get(binIndex).getBinningStrategy().get(1));
                    logger.info("Current bin size on creating: {}", bins.size());
                }
                break;
            }
        }   else {
            // if bins is empty, create a bin with given properties
            createBin(packetProperties, getConfig().getSortBy());
            logger.info("Creating new bin on startup >>>>>>>>>>>>>>>>>>>");
            logger.info("Bin not found, creating bin>>>>>>>>>>>>>");
            logger.info("Required properties: "+ packetProperties);
            logger.info("Created properties"+bins.get(binIndex).getBinningStrategy());
            logger.info("Current bin size on creating: {}", bins.size());
        }
        logger.info("Bin index: {}", binIndex);
       return binIndex;
    }


    // match properties
    public Boolean compareArrays(List<String> array1,List<String> array2){

        Boolean isMatched =false;
        for (int i=0; i<array1.size();i++){
           if(array1.get(i) !=array2.get(i)){
               isMatched = false;
               break;
           }else {
               isMatched = true;
           }
        }

        return isMatched;
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
