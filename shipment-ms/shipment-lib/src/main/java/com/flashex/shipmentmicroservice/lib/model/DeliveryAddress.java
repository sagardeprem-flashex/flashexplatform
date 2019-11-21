package com.flashex.shipmentmicroservice.lib.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The following class stores the data of Delivery address, it's geo-location
 * **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryAddress {

    /** String variables **/

    public String addressLine1;
    public String city;
    public String state;

    /** Double variables **/
    public double latitude;
    public double longitude;

    /** Integer variables **/
    public int pincode;
}
