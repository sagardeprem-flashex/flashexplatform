package com.flashex.tripplanningmicroservice.lib.ORTools.bingsupport.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flashex.tripplanningmicroservice.lib.ORTools.genmatrix.Urllib;
import com.flashex.tripplanningmicroservice.lib.model.bingdm.BingDistanceMatrix;
import com.flashex.tripplanningmicroservice.lib.model.DeliveryAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@Service
public class BingServices {

    private static final Logger logger = Logger.getLogger(BingServices.class.getName());

    private static String apiKey = "Ao69VS-uqirrCWu6vKjikx-jBYmIuAmnK2Cwcis647cwcsVpv9f_I12-bAh_4Hk-";
    private static String baseUrl = "https://dev.virtualearth.net/REST/v1/Routes/DistanceMatrix";
    private static String drivingMode = "driving";

    public static long[][] requestDistanceMatrix(List<DeliveryAddress>originAddresses, List<DeliveryAddress>destinationAddresses) throws JsonProcessingException {

        long[][] distMat = new long[originAddresses.size()][destinationAddresses.size()];


        String finalRequestUrl = baseUrl+"?origins=";
        for (int i = 0; i<originAddresses.size(); i++) {
            finalRequestUrl += originAddresses.get(i).getLatitude() + ",";
            finalRequestUrl += originAddresses.get(i).getLongitude();
            if(i+1 == originAddresses.size()){
                finalRequestUrl += "&";
            } else {
                finalRequestUrl += ";";
            }
        }
        for (int i = 0; i<destinationAddresses.size(); i++) {
            finalRequestUrl += destinationAddresses.get(i).getLatitude() + ",";
            finalRequestUrl += destinationAddresses.get(i).getLongitude();
            if(i+1 == destinationAddresses.size()){
                finalRequestUrl += "&";
            } else {
                finalRequestUrl += ";";
            }
        }
        finalRequestUrl += "travelMode=" + drivingMode + "&key=" + apiKey;
        String inline = Urllib.urlopen(finalRequestUrl);
        logger.info("Inline Output -------------------> "+inline);

        BingDistanceMatrix distanceMatrix = new ObjectMapper().readValue(inline, BingDistanceMatrix.class);

        for(int i=0; i<distanceMatrix.getResourceSets().get(0).getResources().get(0).getResults().size(); i++){
            distMat[distanceMatrix.getResourceSets().get(0).getResources().get(0).getResults().get(i).getDestinationIndex()][distanceMatrix.getResourceSets().get(0).getResources().get(0).getResults().get(i).getOriginIndex()]
                    = (long) distanceMatrix.getResourceSets().get(0).getResources().get(0).getResults().get(i).getTravelDistance();
        }
        logger.info("Generated Distance Matrix -----------------> "+ Arrays.deepToString(distMat));

        return distMat;
    }

    public static long[][] requestTimeMatrix(List<DeliveryAddress>originAddresses, List<DeliveryAddress>destinationAddresses) throws JsonProcessingException {

        long[][] timeMat = new long[originAddresses.size()][destinationAddresses.size()];

        String finalRequestUrl = baseUrl+"?origins=";
        for (int i = 0; i<originAddresses.size(); i++) {
            finalRequestUrl += originAddresses.get(i).getLatitude() + ",";
            finalRequestUrl += originAddresses.get(i).getLongitude();
            if(i+1 == originAddresses.size()){
                finalRequestUrl += "&";
            } else {
                finalRequestUrl += ";";
            }
        }
        for (int i = 0; i<destinationAddresses.size(); i++) {
            finalRequestUrl += destinationAddresses.get(i).getLatitude() + ",";
            finalRequestUrl += destinationAddresses.get(i).getLongitude();
            if(i+1 == destinationAddresses.size()){
                finalRequestUrl += "&";
            } else {
                finalRequestUrl += ";";
            }
        }
        finalRequestUrl += "travelMode=" + drivingMode + "&key=" + apiKey;
        String inline = Urllib.urlopen(finalRequestUrl);
        BingDistanceMatrix distanceMatrix = new ObjectMapper().readValue(inline, BingDistanceMatrix.class);

        for(int i=0; i<distanceMatrix.getResourceSets().get(0).getResources().get(0).getResults().size(); i++){
            timeMat[distanceMatrix.getResourceSets().get(0).getResources().get(0).getResults().get(i).getDestinationIndex()][distanceMatrix.getResourceSets().get(0).getResources().get(0).getResults().get(i).getOriginIndex()]
                    = (long) distanceMatrix.getResourceSets().get(0).getResources().get(0).getResults().get(i).getTravelDuration();
        }

        return timeMat;
    }
}
