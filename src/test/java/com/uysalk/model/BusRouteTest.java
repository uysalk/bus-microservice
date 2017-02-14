package com.uysalk.model;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Created by uysal.kara on 14.02.2017.
 */
public class BusRouteTest {
    @Test
    public void locationsAfterIndex() throws Exception {

        BusRoute route = new BusRoute(1000, new String[]{"0", "1", "2", "3", "4"} );

        assertEquals (route.locationsAfterIndex(1), Arrays.asList(1, 2, 3, 4));


    }

}