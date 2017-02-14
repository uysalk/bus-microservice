package com.uysalk.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by uysal.kara on 14.02.2017.
 */
public class BusRoute {



    private final Integer id;
    private final int[] locations;

    public BusRoute(Integer id, String[] locationArray){
       this.locations = Arrays.asList(locationArray).stream().mapToInt(Integer::parseInt).toArray();

        this.id = id;

    }

    public Integer getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BusRoute busRoute = (BusRoute) o;

        return id.equals(busRoute.id) ;
    }



    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public Collection<Integer> locationsAfterIndex(Integer idx) {
        return  IntStream.of(Arrays.copyOfRange(locations, idx, locations.length)).boxed().collect(Collectors.toList());
    }
}
