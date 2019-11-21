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

public class VrpWithDroppingVisit {



    /** Minimal VRP.*/
    static {
        System.loadLibrary("jniortools");
    }

    private static final Logger logger = Logger.getLogger(VrpWithDroppingVisit.class.getName());

    static class DataModel {


        GenerateMatrix matGenerator = new GenerateMatrix();
        Data d = matGenerator.createData();
        public final int[][] distanceMatrix = matGenerator.createDistanceMatrix(d);
        public final String[] addresses = d.addr;
        public final String Key = d.API_Key;
        public final int[][] distmat = matGenerator.createDistanceMatrix(d);
        public final int[][] timemat = matGenerator.createTimeTravelMatrix(d);


        public final long[] demands = {0, 1, 1, 2, 4, 2, 4, 8, 8, 1, 2, 1, 2, 4, 4, 8};
        public final long[] vehicleCapacities = {30, 15, 17, 18};
        public final int vehicleNumber = 4;
        public final int depot = 0;

        DataModel() throws ParseException {
        }
    }

    /// @brief Print the solution.
    static void printSolution(
            DataModel data, RoutingModel routing, RoutingIndexManager manager, Assignment solution, String[] address) throws Exception {

        String[] addr = address;
        HashMap<String, Set<String>> Locationcord = new HashMap();


        // Display dropped nodes.
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

        // Display routes
        long totalDistance = 0;
        long totalLoad = 0;

        for (int i = 0; i < data.vehicleNumber; ++i) {
            long index = routing.start(i);
            logger.info("Route for Vehicle " + i + ":");
            long routeDistance = 0;
            long routeLoad = 0;
            String response = "";
            String route = "";
            Set<String> latlongarr = new HashSet<String>();
            while (!routing.isEnd(index)) {
                long nodeIndex = manager.indexToNode(index);
                routeLoad += data.demands[(int) nodeIndex];
                route += nodeIndex + " Load(" + routeLoad + ")  -> " ; //+ "Address" + addr[(int) nodeIndex] + "-->";
                response = geocode(addr[(int) nodeIndex],data.Key);
                System.out.println(latlongarr.size());

                latlongarr.add(response);

                long previousIndex = index;
                index = solution.value(routing.nextVar(index));
                routeDistance += routing.getArcCostForVehicle(previousIndex, index, i);
            }

            Locationcord.put("Vehicle:" + i,latlongarr);

            route += manager.indexToNode(routing.end(i));
            logger.info(route);
            logger.info("Distance of the route: " + routeDistance + "m");
            logger.info("Load of the route:" + routeLoad);
            totalDistance += routeDistance;
            totalLoad += routeLoad;

            logger.info("Array of lat & long" + latlongarr);
            logger.info("Key value" + Locationcord);

        }
        logger.info("Total Distance of all routes: " + totalDistance + "m");
        logger.info("Total Load of all routes: " + totalLoad);
    }

    static void matPrint(int[][] distmat, int[][] timemat, String[] address) {
        long[][] dist_mat = new long[distmat.length][distmat.length];
        long[][] time_mat = new long[timemat.length][timemat.length];

        for (int i = 0, j = 0; i < dist_mat.length || j < time_mat.length; i = Math.min(dist_mat.length + 1, i + 1), j = Math.min(time_mat.length + 1, j + 1)) {
        System.out.println(dist_mat[i].length + " Distance Matrix " + Arrays.toString(distmat[i]) + "\n" +
                time_mat[i].length + " Time Travel " + Arrays.toString(timemat[i]) + "\n");
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

        // Create Routing Index Manager
        RoutingIndexManager manager =
                new RoutingIndexManager(data.distanceMatrix.length, data.vehicleNumber, data.depot);

        // Create Routing Model.
        RoutingModel routing = new RoutingModel(manager);

        // Create and register a transit callback.
        final int transitCallbackIndex =
                routing.registerTransitCallback((long fromIndex, long toIndex) -> {
                    // Convert from routing variable Index to user NodeIndex.
                    int fromNode = manager.indexToNode(fromIndex);
                    int toNode = manager.indexToNode(toIndex);
                    return data.distanceMatrix[fromNode][toNode];
                });

        // Define cost of each arc.
        routing.setArcCostEvaluatorOfAllVehicles(transitCallbackIndex);

        // Add Capacity constraint.
        final int demandCallbackIndex = routing.registerUnaryTransitCallback((long fromIndex) -> {
            // Convert from routing variable Index to user NodeIndex.
            int fromNode = manager.indexToNode(fromIndex);
            return data.demands[fromNode];
        });
        routing.addDimensionWithVehicleCapacity(demandCallbackIndex, 0, // null capacity slack
                data.vehicleCapacities, // vehicle maximum capacities
                true, // start cumul to zero
                "Capacity");

        // Allow to drop nodes.
        long penalty = 50000;
        for (int i = 1; i < data.distanceMatrix.length; ++i) {
            routing.addDisjunction(new long[] {manager.nodeToIndex(i)}, penalty);
        }

        // Setting first solution heuristic.
        RoutingSearchParameters searchParameters =
                main.defaultRoutingSearchParameters()
                        .toBuilder()
                        .setFirstSolutionStrategy(FirstSolutionStrategy.Value.PATH_CHEAPEST_ARC)
                        .build();

        // Solve the problem.
        Assignment solution = routing.solveWithParameters(searchParameters);

        // Print solution on console.
        printSolution(data, routing, manager, solution,data.addresses);


//                Prints distance and time matrices
    matPrint(data.distmat,data.timemat,data.addresses);

    }
}

