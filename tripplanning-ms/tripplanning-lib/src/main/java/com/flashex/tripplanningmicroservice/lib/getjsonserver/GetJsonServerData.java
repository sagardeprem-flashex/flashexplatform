package com.flashex.tripplanningmicroservice.lib.getjsonserver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flashex.tripplanningmicroservice.lib.ORTools.VrpWithCapacityConstraint;
import com.flashex.tripplanningmicroservice.lib.ORTools.genmatrix.Urllib;
import com.flashex.tripplanningmicroservice.lib.model.Vehicle;
import com.flashex.tripplanningmicroservice.lib.model.VehicleList;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class GetJsonServerData {

    private String inline;

    public List<Vehicle> processJsonData() throws IOException {

        final Logger logger = Logger.getLogger(GetJsonServerData.class.getName());

        String request = "http://vehicle-json-server:3000/vehicles";
        String inline = Urllib.urlopen(request);
        //         get data from json server
        logger.info("Recieved vehicle data-----------------------> "+inline);
        List<Vehicle> fleetdetails = new ObjectMapper().readValue(inline, new TypeReference<List<Vehicle>>(){});
        logger.info("Fleet details after parsing ----------------> "+ fleetdetails);
        List<Vehicle> availableVehicles = new ArrayList<Vehicle>();
        for(int i=0; i<fleetdetails.size(); i++) {
            if(Boolean.parseBoolean(fleetdetails.get(i).getAvailability())) {
                availableVehicles.add(fleetdetails.get(i));
                logger.info("Available vehicle -------------------> "+ fleetdetails.get(i));
            }
        }
        logger.info("No of available vehicles ------> "+availableVehicles.size());
        return availableVehicles;

//        URL url = new URL("http://vehicle-json-server:3000/vehicles");
//        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
//        conn.setRequestMethod("GET");
//        conn.connect();
//        int responseCode = conn.getResponseCode();
//
//        if(responseCode != 200) {
//            throw new RuntimeException("HttpResponseCode: "+ responseCode);
//        }
//        else
//        {
//            Scanner sc = new Scanner(url.openStream());
//            while(sc.hasNext())
//            {
//                inline += sc.nextLine();
//            }
//            sc.close();
//        }
//
//        logger.info("Obtained JSON response in string format ----------------> "+ inline+"--------");
//        VehicleList fleetdetails = new ObjectMapper().readValue(inline, VehicleList.class);
//
//        logger.info("Fleet details after parsing ----------------> "+ fleetdetails);
//        return fleetdetails;

    }
}
