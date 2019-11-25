package com.flashex.shipmentmicroservice.lib.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Date;

@Table("Packet")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Packet {

    /** String variables **/

    @PrimaryKey
    private String orderId;
    private String productId;
    private String orderDescription;
    private String deliveryDescription;
    private String orderType;
    private String priority;
    private String status;

    /** Float variables **/
    private float weight;
    private float length;
    private float breadth;
    private float height;
    private float costOfOrder;


    /** Date type variables**/
    private Date receivedDate;

    //to be updated by Trip Planning microservice
    private Date estimatedDeliveryDate;

    //to be updated by Trip Itinerary microservice
    private Date actualDeliveryDate;

    /** Enum type variables **/


    /** Objects of same package**/
//    private DeliveryAddress deliveryAddress;
//    private Customer customer;


}
