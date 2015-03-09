package com.ulabs.services;

import com.ulabs.controllers.Station;
import org.springframework.stereotype.Component;

/**
 * Created by julienmonnier on 09/03/2015.
 */
@Component
public class StationsProvider {

    public StationsProvider(){

        System.out.println("station provider created");
    }

    public Station getStation(String name) {

        System.out.println("user requested station: "+ name);
        return null;
    }

}
