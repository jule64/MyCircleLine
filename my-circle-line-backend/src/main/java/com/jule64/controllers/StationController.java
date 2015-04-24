package com.jule64.controllers;

import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import com.jule64.model.Station;
import com.jule64.services.StationsProvider;
import com.jule64.shared.Shared;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class StationController {

    @Autowired
    private StationsProvider stationsProvider;

    private static final String template = "Station, %s!";
    private final AtomicLong counter = new AtomicLong();
    Logger logger = Logger.getLogger(Shared.MYCIRCLELINE_LOGGER);

    @RequestMapping("/stations")
    public Station tubes(HttpServletRequest request, @RequestParam(value="code", defaultValue="LST") String code) {

        final String userIpAddress = request.getRemoteAddr();
        final String userAgent = request.getHeader("user-agent");

        logger.info("user "+userIpAddress+" requested station: " + code);
        Station station = stationsProvider.getStation(code);

        return station;

    }

}
