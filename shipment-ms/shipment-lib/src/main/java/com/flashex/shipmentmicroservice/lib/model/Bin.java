package com.flashex.shipmentmicroservice.lib.model;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Bin {

    private String binId;
    private Date createdOn;
    private List<String> binningStrategy;
    private String sortingStrategy;
    private List<Packet> binnedPackets;

}
