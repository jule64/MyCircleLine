package com.jule64.controllers;



import java.util.Collection;
import java.util.logging.Logger;

import com.jule64.model.Station;
import com.jule64.services.StationsProvider;
import com.jule64.shared.Shared;
import org.apache.catalina.connector.RequestFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by julienmonnier on 26/03/2015.
 */

@RestController
public class StationsListController {

    @Autowired
    private StationsProvider stationsProvider;

    Logger logger = Logger.getLogger(Shared.MYCIRCLELINE_LOGGER);

    @RequestMapping("/allstations")
    public Collection<Station> allStations(HttpServletRequest request) {

        final String userIpAddress;
        if(request instanceof RequestFacade) {
            userIpAddress = ((RequestFacade) request).getHeader("x-real-ip");

        } else {
            userIpAddress = request.getRemoteAddr();
        }
        final String userAgent = request.getHeader("user-agent");
        logger.info("user "+userIpAddress+" requested all stations list");

        return stationsProvider.getStationsList();

    }

}
