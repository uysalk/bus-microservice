package com.uysalk.model;

/**
 * Created by uysal.kara on 14.02.2017.
 */

import com.uysalk.io.FileParser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class DataModel implements CommandLineRunner {


    private Map<Integer, Location> locationModel =  new HashMap<>();


    private Map<Integer, BusRoute> routeModel;
    private String path;


    public void run(String... args) throws IOException {
        this.path = args.length > 0 ? args[0]:"input.txt";
        refresh ();

    }

    public void refresh () throws IOException {
        System.out.println ("Refreshing data model from " + path);
        FileParser fileParser = new FileParser();
        fileParser.parseFile(path);
        locationModel = fileParser.getLocationModel();
        routeModel = fileParser.getRouteModel();

    }

    public Map<Integer, Location> getLocationModel() {
        return locationModel;
    }

    public Map<Integer, BusRoute> getRouteModel() {
        return routeModel;
    }
}


