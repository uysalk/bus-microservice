package com.uysalk.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by uysal.kara on 14.02.2017.
 */
public class Location {



    private final Integer id;

    

    private Map<Integer, BusRouteWithIndex> routes = new HashMap<>();


    public Location (Integer id){
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        return id.equals(location.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public void addRoute(BusRouteWithIndex busRouteWithIndex) {
        routes.put(busRouteWithIndex.getRouteId(),busRouteWithIndex );

    }
    public Map<Integer, BusRouteWithIndex> getRoutes() {
        return routes;
    }
 
    
    //Route is valid if  they are on the same bus route and pickup index is smaller than dropoff index
    public List<Integer> validRoutes(Location dropoffLocation) {
        List<Integer> validPaths = new ArrayList<>();

        routes.values().stream().forEach(x ->{
                if (dropoffLocation.getRoutes().containsKey(x.getRouteId())){
                    BusRouteWithIndex dropOffRoute = dropoffLocation.getRoutes().get(x.getRouteId());
                    if (dropOffRoute.getIdx() > x.getIdx()) validPaths.add (x.getRouteId());
                }
        });

        return validPaths;
    }
    

    public static class BusRouteWithIndex {


        private final Integer routeId;
        private final Integer idx;

        public BusRouteWithIndex (Integer routeId, Integer idx){

            this.routeId = routeId;
            this.idx = idx;
        }

        public Integer getRouteId() {
            return routeId;
        }

        public Integer getIdx() {
            return idx;
        }

    }


}
