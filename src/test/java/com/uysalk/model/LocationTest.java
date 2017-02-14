package com.uysalk.model;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by uysal.kara on 14.02.2017.
 */
public class LocationTest {

    @Test
    public void validRouteWithDirectPath() throws Exception {

        Location pickup = new Location(0);
        pickup.addRoute(new Location.BusRouteWithIndex(1000,1));

        Location dropoff = new Location(3);

        dropoff.addRoute(new Location.BusRouteWithIndex(1000,3));

        List<Integer> validRoutes = pickup.validRoutes(dropoff);

        assertEquals (validRoutes.get(0), Integer.valueOf(1000)); // from location 0 to 3 there is a direct route...


    }

    @Test
    public void invalidRouteWithReverseIndexes() throws Exception {

        Location pickup = new Location(0);
        pickup.addRoute(new Location.BusRouteWithIndex(1000,3));

        Location dropoff = new Location(3);

        dropoff.addRoute(new Location.BusRouteWithIndex(1000,1));

        List<Integer> validRoutes = pickup.validRoutes(dropoff);

        assertTrue(validRoutes.isEmpty()); // from location 0 to 3 there is no direct route...


    }




    @Test
    public void invalidRouteWithNoDirectPath() throws Exception {

        Location pickup = new Location(0);
        pickup.addRoute(new Location.BusRouteWithIndex(1000,3));

        Location dropoff = new Location(3);

        dropoff.addRoute(new Location.BusRouteWithIndex(1001,1));

        List<Integer> validRoutes = pickup.validRoutes(dropoff);

        assertTrue(validRoutes.isEmpty()); // from location 0 to 3 there is no direct route...


    }

}