package com.flashex.shipmentmicroservice.lib.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@RedisHash("bin")
public class Bin {

    @Id
    private String binId;
//    private int binSize;
    private Date createdOn;
    private List<String> binningStrategy;
    private String sortingStrategy;
    private List<Packet> binnedPackets;

}
