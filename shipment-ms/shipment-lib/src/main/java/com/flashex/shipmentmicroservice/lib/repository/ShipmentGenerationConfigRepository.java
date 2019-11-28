package com.flashex.shipmentmicroservice.lib.repository;

import com.flashex.shipmentmicroservice.lib.model.ShipmentGenerationConfig;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.Optional;

public interface ShipmentGenerationConfigRepository extends CassandraRepository<ShipmentGenerationConfig,String> {
    public Optional<ShipmentGenerationConfig> findByActiveContaining(String isActive);
}
