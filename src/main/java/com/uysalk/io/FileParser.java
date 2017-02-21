package com.uysalk.io;

import com.uysalk.model.BusRoute;
import com.uysalk.model.Location;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by uysal.kara on 14.02.2017.
 */

public class FileParser {

    private Map<Integer, Location> locationModel = new HashMap<>();

    private Map<Integer, BusRoute> routeModel = new HashMap<>();

    public Map<Integer, Location> getLocationModel() {
        return locationModel;
    }

    public Map<Integer, BusRoute> getRouteModel() {
        return routeModel;
    }


    public void parseFile(String path) throws IOException {

        LineIterator it = FileUtils.lineIterator(FileUtils.getFile(path), "UTF-8");


        try {
            if (it.hasNext()) it.nextLine(); //read number of routes.. we don't use it.

            while (it.hasNext()) {
                String line = it.nextLine();
                addToModel(line);
            }
        } finally {
            LineIterator.closeQuietly(it);
        }
    }


    /**
     * Two  type of data structure is being constructed.
     * Location --> Route1(index1),  Route2(index2)
     * Route --> [Location1, Location2]
     */
    public void addToModel(String line) {
        String[] routeInfo = line.split("\\s+");
        Integer busRouteID = Integer.parseInt(routeInfo[0]); // first element is route id

        for (int i = 1; i < routeInfo.length; i++) {
            Integer locationID = Integer.parseInt(routeInfo[i]);
            Location location = null;

            if (locationModel.containsKey(locationID)) {
                location = locationModel.get(locationID);
            } else {
                location = new Location(locationID);
                locationModel.put(locationID, location);

            }

            location.addRoute(new Location.BusRouteWithIndex(busRouteID, i));
            routeModel.put(busRouteID, new BusRoute(busRouteID, Arrays.copyOfRange(routeInfo, 1, routeInfo.length))); // first element is route id
        }
    }


}
