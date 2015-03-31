package com.ulabs.controllers;



import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import com.ulabs.model.Station;
import com.ulabs.services.StationsProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
/**
 * Created by julienmonnier on 26/03/2015.
 */

@RestController
public class StationsListController {

    @Autowired
    private StationsProvider stationsProvider;

    @RequestMapping("/allstations")
    public Collection<Station> allStations() {

        return stationsProvider.getStationsList();

    }

}
