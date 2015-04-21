package com.jule64.controllers;



import java.util.Collection;

import com.jule64.model.Station;
import com.jule64.services.StationsProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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
