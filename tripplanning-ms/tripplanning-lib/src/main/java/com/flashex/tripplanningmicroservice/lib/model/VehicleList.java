package com.flashex.tripplanningmicroservice.lib.model;

import java.util.ArrayList;

public class VehicleList {

    private ArrayList<Vehicle> listofvehicle;

    public int getNoOfVehicle(){
        int vehiclelistsize = this.listofvehicle.size();
        return vehiclelistsize;
    }

    public long[] vehicleCapacity(){
        long[] vehiclecapacities = new long[getNoOfVehicle()];
        for (int i = 0 ; i<getNoOfVehicle();i++){
            vehiclecapacities[i] = this.listofvehicle.get(i).getVehicleVolume();
        }
        return vehiclecapacities;
    }

    public String[] getCrewDetails(){
        String[] crewdetails = new String[getNoOfVehicle()];
        for (int i = 0 ; i<getNoOfVehicle();i++){
            crewdetails[i] = this.listofvehicle.get(i).getVehicleCrew();
        }
        return crewdetails;
    }

    public boolean[] availability(){
        boolean[] vehicleavailabilty = new boolean[getNoOfVehicle()];
        for (int i = 0 ; i<getNoOfVehicle();i++){
            vehicleavailabilty[i] = Boolean.parseBoolean(this.listofvehicle.get(i).getAvailability());
        }
        return vehicleavailabilty;
    }

}
