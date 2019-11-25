package com.flashex.shipmentmicroservice.webservice.controller;


import com.flashex.shipmentmicroservice.lib.model.Packet;
import com.flashex.shipmentmicroservice.lib.services.PacketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")

public class PacketController {

    @Autowired
    private PacketService packetService;

    @GetMapping("/packets")
    public List<Packet> getAllPackets() {
        System.out.println("in get All Packets");
        return packetService.getAllPackets();
    }

    @PostMapping("/packets")
    public Packet saveOrder(@RequestBody Packet packetData)
    {
        return packetService.savePacket(packetData);
    }

}
