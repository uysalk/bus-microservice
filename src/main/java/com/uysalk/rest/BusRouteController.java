package com.uysalk.rest;

import com.uysalk.model.DataModel;
import com.uysalk.services.BusRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by uysal.kara on 14.02.2017.
 */
@RestController
@RequestMapping("/api")
public class BusRouteController {

    @Autowired
    private BusRouteService busRouteService;

    @Autowired
    private DataModel dataModel;

    @RequestMapping(value="/direct", method= RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> direct (@RequestParam(value="pickup_id")Integer pickupId, @RequestParam(value="dropoff_id")Integer dropOffId ){
        List<Integer> routes = busRouteService.directRoutes(pickupId, dropOffId);
        Map<String, Object>  response = new HashMap<>();
        response.put ("pickup_id", pickupId);
        response.put ("dropoff_id", dropOffId);
        response.put ("has_direct_route", routes.size()>0);
        if (routes.size()>0) {
            response.put("direct_routes", routes);
        }
        return response;

    }

    @RequestMapping(value="/refresh", method= RequestMethod.GET)
    @CacheEvict(value = {"indirectRoutes","directRoutes"},  allEntries = true)
    public String refresh () throws IOException {
        dataModel.refresh();
        return "OK";
    }

    @RequestMapping(value="/indirect", method= RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> indirect (@RequestParam(value="pickup_id")Integer pickupId, @RequestParam(value="dropoff_id")Integer dropOffId ){
        List<Integer> routes = busRouteService.indirectRoutes(pickupId, dropOffId);
        Map<String, Object>  response = new HashMap<>();
        response.put ("pickup_id", pickupId);
        response.put ("dropoff_id", dropOffId);
        response.put ("has_indirect_route", routes.size()>0);
        if (routes.size()>0) {
            response.put("indirect_route", routes);
        }
        return response;

    }


}
