package com.flashex.shipmentmicroservice.lib.repository;

import com.flashex.shipmentmicroservice.lib.model.Packet;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PacketRepository extends CassandraRepository<Packet, UUID> {

//public interface OrderRepository extends CassandraRepository<Packet, UUID> {

}
