package com.flashex.tripplanningmicroservice.lib.services;

import com.flashex.tripplanningmicroservice.lib.ORTools.TimeWindowDelivery;
import com.flashex.tripplanningmicroservice.lib.ORTools.VrpWithCapacityConstraint;
import com.flashex.tripplanningmicroservice.lib.ORTools.VrpWithDroppingVisit;
import com.flashex.tripplanningmicroservice.lib.ORTools.genmatrix.Data;
import com.flashex.tripplanningmicroservice.lib.ORTools.genmatrix.GenerateMatrix;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.logging.Logger;

//  This service belongs to VRP problem with capacity constraint

@Service
@EnableConfigurationProperties(ServiceProperties.class)
public class ORService {

    private static final Logger logger = Logger.getLogger(ORService.class.getName());

    private  ServiceProperties serviceProperties;
    private  VrpWithCapacityConstraint vrpWithCapacityConstraint;
    private  VrpWithDroppingVisit vrpWithDroppingVisit;
    private  TimeWindowDelivery timeWindowDelivery;
    private Data data;

    public Data getData() {
        return this.data;
    }

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

    //    Send array of address
    public void settingAddressArray(String[] address){
        data.setAddr(address);
        logger.info("-----------In OR Service----------"+ Arrays.toString(data.getAddr()));
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



}
