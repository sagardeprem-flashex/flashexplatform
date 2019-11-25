package com.flashex.tripplanningmicroservice.lib.model;


import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class Vehicle {

    private String vehicleId;
    private String vehicleType;
    private String registrationNum;
    private Float vehicleVolume;
    private boolean availibility;
    private List<User> vehicleCrew;

    User user = new User();
    public String check(){
        return user.getUserId();
    }

}
