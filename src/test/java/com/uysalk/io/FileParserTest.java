package com.uysalk.io;

import com.uysalk.model.BusRoute;
import com.uysalk.model.Location;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * Created by uysal.kara on 14.02.2017.
 */
public class FileParserTest {
    @Test
    public void addToModel() throws Exception {
        FileParser parser = new FileParser();
        parser.addToModel("1000 0 1 2 3 4");

        BusRoute busRoute = parser.getRouteModel().get(1000);

        assertNotNull (busRoute);

        Location location = parser.getLocationModel().get(0);
        Location.BusRouteWithIndex busRouteWithIndex = location.getRoutes().get(1000);
        assertEquals (busRouteWithIndex.getIdx(),Integer.valueOf(  1)); // location 0 is at position 1 at route 1000.

        parser.addToModel("1002 0 6 4");

        busRouteWithIndex = location.getRoutes().get(1002);
        assertEquals (busRouteWithIndex.getIdx(),Integer.valueOf(  1)); // location 0 is at position 1 at route 1002.


        parser.addToModel("1003  6  5 0");
        busRouteWithIndex = location.getRoutes().get(1003);
        assertEquals (busRouteWithIndex.getIdx(),Integer.valueOf(  3)); // location 0 is at position 3 at route 1003.


    }

}