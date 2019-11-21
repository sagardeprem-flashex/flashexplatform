package com.flashex.tripplanningmicroservice.lib.ORTools;


import com.flashex.tripplanningmicroservice.lib.ORTools.genmatrix.Data;
import com.flashex.tripplanningmicroservice.lib.ORTools.genmatrix.GenerateMatrix;
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

        /** Minimal VRP.*/
        static { System.loadLibrary("jniortools"); }

        private static final Logger logger = Logger.getLogger(TimeWindowDelivery.class.getName());

        static class DataModel {


        GenerateMatrix matGenerator = new GenerateMatrix();
        Data d = matGenerator.createData();
        public final int[][] timeMatrix = matGenerator.createDistanceMatrix(d);
        public final String[] addresses = d.addr;
        public final String Key = d.API_Key;
        public final int[][] distmat = matGenerator.createDistanceMatrix(d);
        public final int[][] timemat = matGenerator.createTimeTravelMatrix(d);


            public final long[][] timeWindows = {
                    {0, 5},    // depot
                    {7, 12},   // 1
                    {10, 15},  // 2
                    {16, 18},   // 3
                    {10, 13},   // 4
                    {0, 5},    // 5
                    {5, 10},   // 6
                    {0, 4},   // 7
                    {5, 10},   // 8
                    {0, 3},    // 9
                    {10, 16},  // 10
                    {10, 15},  // 11
                    {0, 5},    // 12
                    {5, 10},   // 13
                    {7, 8},   // 14
                    {10, 15},  // 15
                    {11, 15},   // 16
            };


            public final int  vehicleNumber = 4;
            public final int  depot = 0;

            DataModel() throws ParseException {
            }
        }

        /// @brief Print the solution.
        static void printSolution(
            DataModel data, RoutingModel routing, RoutingIndexManager manager, Assignment solution,String[] address) throws Exception {

            RoutingDimension timeDimension = routing.getMutableDimension("Time");

        String[] addr = address; // use when using gentmat to run
        HashMap<String, Set<String> > Locationcord = new HashMap();

            long totalTime = 0;
            for (int i = 0; i < data.vehicleNumber; ++i) {
                long index = routing.start(i);
                logger.info("Route for Vehicle " + i + ":");
                String route = "";
                String response = "";
                Set<String> latlongarr = new HashSet<String>();

                while (!routing.isEnd(index)) {
                    IntVar timeVar = timeDimension.cumulVar(index);
                    long nodeIndex = manager.indexToNode(index);
                    route += manager.indexToNode(index) + " Time(" + solution.min(timeVar) + ","
                            + solution.max(timeVar) + ") -> " + "Address" + addr[(int) nodeIndex] + "-->";
                response = geocode(addr[(int) nodeIndex],data.Key);
//                System.out.println(latlongarr.size()); // To print size of latlongarrray
                latlongarr.add(response);
                index = solution.value(routing.nextVar(index));
                }

                Locationcord.put("Vehicle:" + i,latlongarr);

                IntVar timeVar = timeDimension.cumulVar(index);
                route += manager.indexToNode(index) + " Time(" + solution.min(timeVar) + ","
                        + solution.max(timeVar) + ")";
                logger.info(route);
                logger.info("Time of the route: " + solution.min(timeVar) + "s");
                totalTime += solution.min(timeVar);

                logger.info("Array of lat & long" + latlongarr);
                logger.info("Key value" + Locationcord);
            }

            logger.info("Total time of all routes: " + totalTime + "s");
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
            routing.addDimension(transitCallbackIndex, // transit callback
                    30, // allow waiting time
                    30, // vehicle maximum capacities
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