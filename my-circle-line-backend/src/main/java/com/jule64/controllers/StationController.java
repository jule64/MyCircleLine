package com.jule64.controllers;

import java.util.concurrent.atomic.AtomicLong;

import com.jule64.model.Station;
import com.jule64.services.StationsProvider;
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
    public Station tubes(@RequestParam(value="code", defaultValue="LST") String code) {

        Station station = stationsProvider.getStation(code);

        return station;

    }

}
