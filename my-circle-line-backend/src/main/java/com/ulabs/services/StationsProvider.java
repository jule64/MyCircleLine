package main.java.com.ulabs.services;

import com.ulabs.model.Station;
import com.ulabs.parsers.tfl.TubeLineParser;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by julienmonnier on 09/03/2015.
 */
@Component()
public class StationsProvider {

    Logger logger = Logger.getLogger(this.getClass().getName());
    private Map<String, Station> stations = new HashMap<String, Station>();

    public StationsProvider(){
        //TODO add a parserReady method
        TubeLineParser.startParser(stations);
        logger.info("station provider created");
    }

    public Station getStation(String code) {

        logger.info("user XX requested station: " + code);
        return stations.get(code);
    }

}
