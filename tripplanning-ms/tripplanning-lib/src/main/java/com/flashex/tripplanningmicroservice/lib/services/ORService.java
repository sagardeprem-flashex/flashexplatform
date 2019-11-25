package com.flashex.tripplanningmicroservice.lib.services;

import com.flashex.tripplanningmicroservice.lib.ORTools.TimeWindowDelivery;
import com.flashex.tripplanningmicroservice.lib.ORTools.VrpWithCapacityConstraint;
import com.flashex.tripplanningmicroservice.lib.ORTools.VrpWithDroppingVisit;
import com.flashex.tripplanningmicroservice.lib.ORTools.genmatrix.Data;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

//  This service belongs to VRP problem with capacity constraint

@Service
@EnableConfigurationProperties(ServiceProperties.class)
public class ORService {


    private final ServiceProperties serviceProperties;
    private final VrpWithCapacityConstraint vrpWithCapacityConstraint;
    private final VrpWithDroppingVisit vrpWithDroppingVisit;
    private final TimeWindowDelivery timeWindowDelivery;
    private final Data data;

    public ORService(ServiceProperties serviceProperties, VrpWithCapacityConstraint vrpWithCapacityConstraint, VrpWithDroppingVisit vrpWithDroppingVisit, TimeWindowDelivery timeWindowDelivery, Data data) {
        this.serviceProperties = serviceProperties;
        this.vrpWithCapacityConstraint = vrpWithCapacityConstraint;
        this.vrpWithDroppingVisit = vrpWithDroppingVisit;
        this.timeWindowDelivery = timeWindowDelivery;
        this.data = data;
    }

    public String message() {
        return this.serviceProperties.getMessage();
    }

//    VRP with capacity constraint function
    public void VrpfunctionWithCapCons() throws Exception {
        vrpWithCapacityConstraint.FinalResult();
    }

//    VRP with Dropping nodes function
    public void VrpfuncWithDropNode() throws Exception {
        vrpWithDroppingVisit.FinalResult();
    }

//    VRP with Time window constraint
    public void TimeWindowConsFunction() throws Exception {
        timeWindowDelivery.FinalResult();
    }

//    Send array of address
    public void settingAddressArray(String[] address){
        data.setAddr(address);
    }

}
