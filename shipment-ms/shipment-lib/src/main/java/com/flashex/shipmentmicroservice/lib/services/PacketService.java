package com.flashex.shipmentmicroservice.lib.services;

import com.flashex.shipmentmicroservice.lib.model.KafkaStatusMessage;
import com.flashex.shipmentmicroservice.lib.model.Packet;
import com.flashex.shipmentmicroservice.lib.model.Status;
import com.flashex.shipmentmicroservice.lib.repository.PacketRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
//@EnableConfigurationProperties(ServiceProperties.class)
public class PacketService {

    @Autowired
    private PacketRepository packetRepository;

    private static Logger logger = LoggerFactory.getLogger(PacketService.class);

    public List<Packet> getAllPackets()
    {
        return this.packetRepository.findAll();
    }

    public List<Packet> savePackets(List<Packet> packetsToBeSaved){
        for(Packet packet: packetsToBeSaved){

            List<Status> statusList = new ArrayList<>();
            Status status = new Status();
            status.setTimeStamp(new Date());
            status.setStatusValue("RECEIVED");
            statusList.add(status);
            packet.setStatusList(statusList);

            if(packet.getPacketId() == null){


                String packetUuid = UUID.randomUUID().toString();
                packet.setPacketId(packetUuid);
            }
            if(packet.getProductId() == null){
                String productUuid = UUID.randomUUID().toString();
                packet.setProductId(productUuid);
            }

            this.packetRepository.save(packet) ;
        }
        return packetsToBeSaved;
    }

    public Packet findByPacketId(String packetId){
        return this.packetRepository.findById(packetId).get();
    }

    public void updateStatus(KafkaStatusMessage packetStatus){

        // find the packet
//        Packet packet = packetRepository.findByPacketIdContaining(packetStatus.getPacketId()).get();
        logger.info(">>>>>>>>>>>>>>>Before finding packet >>>>>>>>>>>>"+ packetStatus.getPacketId());

        Packet packet = this.findByPacketId(packetStatus.getPacketId());
//        Packet packet = packetRepository.findById().get();
        logger.info(">>>>>>>>>>>>>>>>Found packet >>>>>>>>>>>>"+packet.getPacketId());

        // get statuslist
        List<Status> statusToUpdate = packet.getStatusList();
        logger.info(">>>>>>>>>>>>>>>> Getting status list >>>>>>>>>>>>"+ packet.getStatusList());

        // create new status to be added
        Status status = new Status();
        status.setStatusValue(packetStatus.getStatusValue());
        status.setTimeStamp(packetStatus.getTimeStamp());

        // add status
        statusToUpdate.add(status);

        // set new status list
        packet.setStatusList(statusToUpdate);

        // update in repo
        packetRepository.save(packet);

    }


}
