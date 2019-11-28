package com.flashex.tripplanningmicroservice.lib.ORTools;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.flashex.tripplanningmicroservice.lib.ORTools.genmatrix.Data;
import com.flashex.tripplanningmicroservice.lib.ORTools.genmatrix.GenerateMatrix;
import com.flashex.tripplanningmicroservice.lib.getjsonserver.GetJsonServerData;
import com.flashex.tripplanningmicroservice.lib.model.TripItinerary;
import com.flashex.tripplanningmicroservice.lib.model.VehicleList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.ortools.constraintsolver.Assignment;
import com.google.ortools.constraintsolver.FirstSolutionStrategy;
import com.google.ortools.constraintsolver.IntVar;
import com.google.ortools.constraintsolver.RoutingDimension;
import com.google.ortools.constraintsolver.RoutingIndexManager;
import com.google.ortools.constraintsolver.RoutingModel;
import com.google.ortools.constraintsolver.RoutingSearchParameters;
import com.google.ortools.constraintsolver.main;
import org.json.simple.parser.ParseException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;


public class TimeWindowDelivery {

        /** Minimal VRPTW.*/

        private static final Logger logger = Logger.getLogger(TimeWindowDelivery.class.getName());

        static class DataModel {


        GenerateMatrix matGenerator = new GenerateMatrix();
        Data d = matGenerator.createData();
        public final int[][] timeMatrix = matGenerator.createTimeTravelMatrix(d);
        public final String[] addresses = d.addr;
        public final String Key = d.API_Key;
        public final int[][] distmat = matGenerator.createDistanceMatrix(d);
        public final int[][] timemat = matGenerator.createTimeTravelMatrix(d);

        private final GetJsonServerData getJsonServerData = new GetJsonServerData();
        VehicleList vehicleList = getJsonServerData.processJsonData();

        public final long[] demands = {0, 1, 1, 2, 4, 2, 4, 8, 8, 1, 2, 1, 2, 4, 4, 8};


        public final long[][] timeWindows = {
                {0, 50},    // depot
                {0, 50},   // 1
                {0, 50},// 2
                {0, 50},   // 3
                {0, 50},   // 4
                {0, 50},    // 5
                {0, 50},   // 6
                {0, 50},   // 7
                {0, 50},   // 8
                {0, 50},    // 9
                {0, 50},  // 10
                {0, 50},  // 11
                {0, 50},    // 12
                {0, 50},   // 13
                {0, 50},   // 14
                {0, 50},  // 15
            };


//            public final int  vehicleNumber = 4;
            public final int vehicleNumber = vehicleList.getNoOfVehicle();

            public final long[] vehicleCapacities = vehicleList.vehicleCapacity();
            public final int  depot = 0;

            DataModel() throws ParseException, JsonProcessingException {
            }
        }

        /// @brief Print the solution.
        static TripItinerary printSolution(
            DataModel data, RoutingModel routing, RoutingIndexManager manager, Assignment solution,String[] address) throws Exception {

            RoutingDimension timeDimension = routing.getMutableDimension("Time");

        String[] addr = address; // use when using gentmat to run
        HashMap<String, Set<String> > Locationcord = new HashMap();

            TripItinerary tripItinerary = new TripItinerary();

            tripItinerary.setPlannedStartTime("9 AM");
            tripItinerary.getPlannedStartTime();

            tripItinerary.setPlannedEndTime("5 PM");
            tripItinerary.getPlannedEndTime();

//      Setting vehicle details
            VehicleList vehicleList = new VehicleList();
//        logger.info((""+ vehicleList.listofvehicle));

            String droppedNodes = "Dropped nodes:";
            for (int node = 0; node < routing.size(); ++node) {
                if (routing.isStart(node) || routing.isEnd(node)) {
                    continue;
                }
                if (solution.value(routing.nextVar(node)) == node) {
                    droppedNodes += " " + manager.indexToNode(node);
                }
            }
            logger.info(droppedNodes);

            long routeDistance = 0;
            long routeLoad = 0;
            long totalTime = 0;
            for (int i = 0; i < data.vehicleNumber; ++i) {
                long index = routing.start(i);
                logger.info("Route for Vehicle " + i + ":");

                tripItinerary.setVehicle(vehicleList.listofvehicle.get(i));  // set vehicle object

                String route = "";
                String response = "";
                Set<String> latlongarr = new HashSet<String>();

                while (!routing.isEnd(index)) {
                    IntVar timeVar = timeDimension.cumulVar(index);
                    long nodeIndex = manager.indexToNode(index);
                    routeLoad += data.demands[(int) nodeIndex]; // wasnot here before I put it here for calculating occupied vehicle volume

                    route += manager.indexToNode(index) + " Time(" + solution.min(timeVar)*100 + ","
                            + solution.max(timeVar)*100 + ") -> " + "Address" + addr[(int) nodeIndex] + "-->";

                    long vehiclecapacity = data.vehicleCapacities[i]; // Total capacity of a vehicle
                    long occupiedvolume = (((vehiclecapacity - routeLoad)*100)/vehiclecapacity); // gives occupied volume in percentage
                    tripItinerary.setOccupiedVolume(occupiedvolume); // setting occupied volume

                response = geocode(addr[(int) nodeIndex],data.Key);
//                System.out.println(latlongarr.size()); // To print size of latlongarrray
                latlongarr.add(response);

                long previousIndex = index;
                index = solution.value(routing.nextVar(index));
                routeDistance += routing.getArcCostForVehicle(previousIndex, index, i);
                tripItinerary.setPlannedTotalDistance(routeDistance); // set route distance
                long milage = 21;
                long tripexpense = milage*solution.min(timeVar);
                tripItinerary.setTripExpense(tripexpense);
                }

                Locationcord.put("Vehicle:" + i,latlongarr);

                IntVar timeVar = timeDimension.cumulVar(index);

                route += manager.indexToNode(index) + " Time(" + solution.min(timeVar)*100 + ","
                        + solution.max(timeVar)*100 + ")";

                logger.info(route);
                logger.info("Time of the route: " + solution.min(timeVar)*100 + "m");



                totalTime += solution.min(timeVar);

                logger.info("Array of lat & long" + latlongarr);
                logger.info("Key value" + Locationcord);
            }

            logger.info("Total time of all routes: " + totalTime*100 + "m");

            return tripItinerary;
        }

        static void matPrint(int[][] distmat, int[][] timemat, String[] address) {
            long[][] dist_mat = new long[distmat.length][distmat.length];
            long[][] time_mat = new long[timemat.length][timemat.length];

            for (int i = 0, j = 0; i < dist_mat.length || j < time_mat.length; i = Math.min(dist_mat.length + 1, i + 1), j = Math.min(time_mat.length + 1, j + 1)) {
//            System.out.println(dist_mat[i].length + " Distance Matrix " + Arrays.toString(distmat[i]) + "\n" +
//                    time_mat[i].length + " Time Travel " + Arrays.toString(timemat[i]) + "\n");
            }
            String[] addr = address;
            System.out.println(Arrays.toString(addr));
        }

        private static String geocode(String address, String KEY) throws Exception {

            final GeoApiContext context = new GeoApiContext.Builder()
                    .apiKey(KEY)
                    .build();
            final GeocodingResult[] results;
            try {
                results = GeocodingApi.geocode(context, address).await();
                final Gson gson = new GsonBuilder().setPrettyPrinting().create();
                String response = gson.toJson(results[0].geometry.location);

                return response;
            } catch (final Exception e) {
                throw e;
            }
        }


        public void FinalResult() throws Exception {
            // Instantiate the data problem.
            final DataModel data = new DataModel();


            RoutingIndexManager manager =
                    new RoutingIndexManager(data.timeMatrix.length, data.vehicleNumber, data.depot);

            RoutingModel routing = new RoutingModel(manager);
            final int transitCallbackIndex =
                    routing.registerTransitCallback((long fromIndex, long toIndex) -> {
                        // Convert from routing variable Index to user NodeIndex.
                        int fromNode = manager.indexToNode(fromIndex);
                        int toNode = manager.indexToNode(toIndex);
                        return data.timeMatrix[fromNode][toNode];
                    });
            routing.setArcCostEvaluatorOfAllVehicles(transitCallbackIndex);


            routing.addDimensionWithVehicleCapacity(transitCallbackIndex, // transit callback
                    30, // allow waiting time
                    data.vehicleCapacities, // vehicle maximum capacities
                    false, // start cumul to zero
                    "Time");
            RoutingDimension timeDimension = routing.getMutableDimension("Time");
            // Add time window constraints for each location except depot.
            for (int i = 1; i < data.timeWindows.length; ++i) {
                long index = manager.nodeToIndex(i);
                timeDimension.cumulVar(index).setRange(data.timeWindows[i][0], data.timeWindows[i][1]);
            }
            // Add time window constraints for each vehicle start node.
            for (int i = 0; i < data.vehicleNumber; ++i) {
                long index = routing.start(i);
                timeDimension.cumulVar(index).setRange(data.timeWindows[0][0], data.timeWindows[0][1]);
            }
            for (int i = 0; i < data.vehicleNumber; ++i) {
                routing.addVariableMinimizedByFinalizer(timeDimension.cumulVar(routing.start(i)));
                routing.addVariableMinimizedByFinalizer(timeDimension.cumulVar(routing.end(i)));
            }

//            adding disjunction
            long penalty = 50;
            for (int i = 1; i < data.timeMatrix.length; ++i) {
                routing.addDisjunction(new long[] {manager.nodeToIndex(i)}, penalty);
            }

            RoutingSearchParameters searchParameters =
                    main.defaultRoutingSearchParameters()
                            .toBuilder()
                            .setFirstSolutionStrategy(FirstSolutionStrategy.Value.PATH_CHEAPEST_ARC)
                            .build();
            Assignment solution = routing.solveWithParameters(searchParameters);

        printSolution(data, routing, manager, solution,data.addresses);

//                Prints distance and time matrices
        matPrint(data.distmat,data.timemat,data.addresses);
        }
    }