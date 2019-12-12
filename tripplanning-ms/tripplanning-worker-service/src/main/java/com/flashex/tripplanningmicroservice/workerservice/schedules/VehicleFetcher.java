package com.flashex.tripplanningmicroservice.workerservice.schedules;

import com.flashex.tripplanningmicroservice.lib.getjsonserver.GetJsonServerData;
import com.flashex.tripplanningmicroservice.lib.model.Vehicle;
import com.flashex.tripplanningmicroservice.lib.model.VehicleList;
import com.flashex.tripplanningmicroservice.lib.services.ORService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class VehicleFetcher {

    @Autowired
    GetJsonServerData getJsonServerData;

    @Autowired
    ORService orService;

    private final Logger logger = Logger.getLogger(VehicleFetcher.class.getName());

//    @Scheduled(cron = "0 0 0/24 ? * * ")
    @Scheduled(cron = "0 * 6 * * *")
    public void fetchVehicles() throws IOException {

        logger.info("Fetch Vehicles Triggered at -----------------> "+ LocalDateTime.now());

        List<Vehicle> vehicles = getJsonServerData.getAvailableVehicles();
        logger.info("Obtained list of vehicles -----------------> "+vehicles.toString());

        VehicleList vehicleList = new VehicleList();
        vehicleList.setListofvehicle(vehicles);
        orService.settingVehicleDetails(vehicleList);
        logger.info("Vehicle List has been updated!");
    }
}
