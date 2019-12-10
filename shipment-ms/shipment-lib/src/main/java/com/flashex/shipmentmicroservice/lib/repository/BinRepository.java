package com.flashex.shipmentmicroservice.lib.repository;

import com.flashex.shipmentmicroservice.lib.model.Bin;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BinRepository  extends CrudRepository<Bin, String> {
}
