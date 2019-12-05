package com.flashex.triptrackingmicroservice.lib.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
