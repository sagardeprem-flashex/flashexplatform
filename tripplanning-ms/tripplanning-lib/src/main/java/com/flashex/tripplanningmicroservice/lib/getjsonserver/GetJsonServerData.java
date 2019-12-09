package com.flashex.tripplanningmicroservice.lib.getjsonserver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flashex.tripplanningmicroservice.lib.ORTools.genmatrix.Urllib;
import com.flashex.tripplanningmicroservice.lib.model.VehicleList;

public class GetJsonServerData {

    public VehicleList processJsonData() throws JsonProcessingException {

        String request = "http://localhost:80/vehicles";
        String inline = Urllib.urlopen(request);
        //         get data from json server
        VehicleList fleetdetails = new ObjectMapper().readValue(inline, VehicleList.class);
        return fleetdetails;

    }
}
