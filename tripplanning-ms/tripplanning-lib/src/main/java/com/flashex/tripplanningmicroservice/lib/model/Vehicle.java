package com.flashex.tripplanningmicroservice.lib.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {

    private String vehicleId;
    private String vehicleType;
    private int vehicleVolume;
    private String availability;
    private  String vehicleCrew;

}
