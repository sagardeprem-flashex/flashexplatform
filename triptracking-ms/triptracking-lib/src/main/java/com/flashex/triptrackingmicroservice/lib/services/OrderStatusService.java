package com.flashex.triptrackingmicroservice.lib.services;

import com.flashex.triptrackingmicroservice.lib.model.KafkaStatusMessage;
import com.flashex.triptrackingmicroservice.lib.model.Packet;
import com.flashex.triptrackingmicroservice.lib.model.PacketLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderStatusService {

    @Autowired
    private KafkaStatusMessage KafkaStatusMessage;



    public void scheduledOrder(List<Packet> packets){
        packets.forEach(packet -> {
            KafkaStatusMessage message = new KafkaStatusMessage();
            message.setPacketId(packet.getPacketId());
            message.setTimeStamp(new Date());
            message.setStatusValue("SCHEDULED");
        });
    }

    public void dispatchedOrder(List<PacketLog> packetLog){
        packetLog.forEach(packet -> {
            KafkaStatusMessage message = new KafkaStatusMessage();
            message.setPacketId(packet.getPacketId());
            message.setTimeStamp(new Date());
            message.setStatusValue("DISPATCHED");
    });
    }

    public void deliveredOrder(String packetId){
            KafkaStatusMessage message = new KafkaStatusMessage();
            message.setPacketId(packetId);
            message.setTimeStamp(new Date());
            message.setStatusValue("DELIVERED");

    }

    public void unDeliveredOrder(String packetId) {
//        packets.forEach(packet -> {
        KafkaStatusMessage message = new KafkaStatusMessage();
        message.setPacketId(packetId);
        message.setTimeStamp(new Date());
        message.setStatusValue("UNDELIVERED");
    }

}
