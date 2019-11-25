package com.flashex.tripplanningmicroservice.lib.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Setter
@Getter
public class Packet {

    /** String variables **/
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
    private Date receivedDate;    //to be updated by Trip Planning microservice
    private Date estimatedDeliveryTimestamp;    //to be updated by Trip Itinerary microservice
    private Date actualDeliveryTimestamp;

    /** Objects of same package**/
    private DeliveryAddress deliveryAddress ;
    private Customer customer;
}
