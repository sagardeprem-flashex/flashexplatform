package com.flashex.shipmentmicroservice.lib.services;

import com.flashex.shipmentmicroservice.lib.model.Packet;
import com.flashex.shipmentmicroservice.lib.repository.PacketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
//@EnableConfigurationProperties(ServiceProperties.class)
public class PacketService {

    @Autowired
    private PacketRepository packetRepository;


    public List<Packet> getAllPackets()
    {
        return this.packetRepository.findAll();
    }

    public List<Packet> savePackets(List<Packet> packetsToBeSaved){

        for(Packet packet: packetsToBeSaved){
            String packetUuid = UUID.randomUUID().toString();
            String productUuid = UUID.randomUUID().toString();
            packet.setPacketId(packetUuid);
            packet.setProductId(productUuid);
            this.packetRepository.save(packet) ;
        }
        return packetsToBeSaved;
    }

}
