package com.ulabs.controllers;

import java.util.concurrent.atomic.AtomicLong;

import com.ulabs.services.StationsProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StationController {


    @Autowired
    private StationsProvider stationsProvider;

    private static final String template = "Station, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/stations")
    public Station tubes(@RequestParam(value="name", defaultValue="Liv Street") String name) {

        Station station = stationsProvider.getStation(name);

//        List<Station> stations = new ArrayList<Station>();
//
//        stations.add(new Station(counter.incrementAndGet(),
//                String.format(template, name)));
//        stations.add(new Station(counter.incrementAndGet(),
//                String.format(template, name)));
//        stations.add(new Station(counter.incrementAndGet(),
//                String.format(template, name)));


        return new Station(counter.incrementAndGet(),
                String.format(template, name));

    }

}
