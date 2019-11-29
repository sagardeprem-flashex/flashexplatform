package com.flashex.shipmentmicroservice.lib.repository;

import com.flashex.shipmentmicroservice.lib.model.BinnerConfig;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.Optional;

public interface ShipmentGenerationConfigRepository extends CassandraRepository<BinnerConfig,String> {
    public Optional<BinnerConfig> findByActiveContaining(String isActive);
}
