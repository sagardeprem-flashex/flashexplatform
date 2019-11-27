package com.flashex.shipmentmicroservice.lib.repository;

import com.flashex.shipmentmicroservice.lib.model.Packet;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

//@Repository

public interface PacketRepository extends CassandraRepository<Packet,String> {

//public interface OrderRepository extends CassandraRepository<Packet, UUID> {

}