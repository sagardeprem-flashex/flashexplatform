package com.flashex.tripplanningmicroservice.lib.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Shipment {
    /** String variables **/
    private String shipmentId;
    /** Integer variables**/
    private int MAX_SHIPMENT_SIZE;
    /** Date variables **/
    private Date shipmentDate;
    /** Objects from local package **/
    private ArrayList<Packet> packetList;

    public String[] getAllDeliveryAddresses(){
        int packetlistsize = this.packetList.size();
        String[] address = new String[packetlistsize+1];

        for(int i=1; i<packetlistsize; i++){
            address[i] = this.packetList.get(i-1).getDeliveryAddress().getAddressLine1() + "+" +
                    this.packetList.get(i-1).getDeliveryAddress().getCity()+ "+" +
                    this.packetList.get(i-1).getDeliveryAddress().getState() + "+" +
                    this.packetList.get(i-1).getDeliveryAddress().getPincode();
        }
        return address;
    }

}


