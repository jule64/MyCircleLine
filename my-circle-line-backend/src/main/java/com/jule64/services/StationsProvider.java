package com.jule64.services;

import com.jule64.model.Station;
import com.jule64.parsers.TubeInfoParser;
import com.jule64.shared.Shared;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

/**
 * Created by julienmonnier on 09/03/2015.
 */
@Component()
public class StationsProvider {

    Logger logger = Logger.getLogger(Shared.MYCIRCLELINE_LOGGER);
    private Map<String, Station> stations = new ConcurrentHashMap<String, Station>(128,0.75f,1);

    public StationsProvider(){
        //TODO add a parserReady method
        TubeInfoParser.startParser(stations);
        logger.info("station provider created");
    }

    public Station getStation(String code) {

        return stations.get(code);
    }

    public Collection<Station> getStationsList() {

        return stations.values();
    }

}
