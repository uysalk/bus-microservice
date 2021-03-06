package com.uysalk.services;

import com.uysalk.model.DataModel;
import com.uysalk.model.Location;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by uysal.kara on 14.02.2017.
 */
@Service
public class BusRouteService {

    private DataModel dataModel ;

    //We dont need  @Autowired
    public BusRouteService (DataModel dataModel){
        this.dataModel = dataModel;
    }

    @Cacheable(value = "directRoutes")
    public List<Integer> directRoutes(Integer pickupLocationId, Integer dropoffLocationId ){

        System.out.printf("Calculating directRoutes for  %d , %d%n",pickupLocationId, dropoffLocationId);
        Location pickupLocation         = dataModel.getLocationModel().get(pickupLocationId);
        Location dropoffLocation        = dataModel.getLocationModel().get(dropoffLocationId);
        if (pickupLocation== null || dropoffLocation == null) return Collections.emptyList(); //  bus is not for us!

        return pickupLocation.validRoutes (dropoffLocation);


    }



    @Cacheable(value = "indirectRoutes")
    public List<Integer> indirectRoutes(Integer pickupLocationId, Integer dropoffLocationId ) {
        System.out.printf("Calculating indirectRoutes for  %d , %d%n",pickupLocationId, dropoffLocationId);

        Location pickupLocation         = dataModel.getLocationModel().get(pickupLocationId);
        Location dropoffLocation        = dataModel.getLocationModel().get(dropoffLocationId);
        if (pickupLocation== null || dropoffLocation == null) return Collections.emptyList(); //  bus is not for us!

        List<Integer> routes = directRoutes(pickupLocationId, dropoffLocationId);
        if (routes.size()>0) return routes;
        else{
            Set<Integer> locationsToBeConsidered = new HashSet<>();

            Set<Integer> alreadyConsidered = new HashSet<>();
            alreadyConsidered.add(pickupLocationId);

            pickupLocation.getRoutes().values().forEach(x->{

                locationsToBeConsidered.addAll(dataModel.getRouteModel().get(x.getRouteId()).locationsAfterIndex (x.getIdx()));

            });
            if (locationsToBeConsidered.iterator().hasNext()){
                return indirectRoutes(locationsToBeConsidered.iterator().next(),dropoffLocationId,locationsToBeConsidered, alreadyConsidered );
            }else
                return Collections.emptyList();
        }


    }

    private List<Integer> indirectRoutes(Integer pickupLocationId, Integer dropoffLocationId, Set<Integer> locationsToBeConsidered, Set<Integer> alreadyConsideredSet) {
        Location pickupLocation         = dataModel.getLocationModel().get(pickupLocationId);

        List<Integer> routes = directRoutes(pickupLocationId, dropoffLocationId);
        if (routes.size()>0) return routes;
        else{
            alreadyConsideredSet.add(pickupLocationId);
            locationsToBeConsidered.remove(pickupLocationId);

            pickupLocation.getRoutes().values().forEach(x->{

                locationsToBeConsidered.addAll(
                        dataModel.getRouteModel().get(x.getRouteId()).locationsAfterIndex (x.getIdx()).stream().filter(i->  ! alreadyConsideredSet.contains(i)).collect(Collectors.toList())
                );

            });
            if (locationsToBeConsidered.iterator().hasNext()){
                return indirectRoutes(locationsToBeConsidered.iterator().next(),dropoffLocationId,locationsToBeConsidered, alreadyConsideredSet );
            }else
                return Collections.emptyList();
        }
    }


}
