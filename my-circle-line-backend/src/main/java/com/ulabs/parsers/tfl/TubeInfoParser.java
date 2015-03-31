package com.ulabs.parsers.tfl;

import com.ulabs.model.Platform;
import com.ulabs.model.Station;
import com.ulabs.model.Train;
import com.ulabs.shared.Shared;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by julienmonnier on 20/03/2015.
 */
public class TubeInfoParser extends Thread {

    Logger logger = Logger.getLogger(Shared.MYCIRCLELINE_LOGGER);

    private DocumentBuilder documentBuilder;
    private Map<String,Station> stations;

    private TubeInfoParser(Map<String, Station> stations) throws ParserConfigurationException {

        this.stations = stations;
        initParser();
    }

    private void initParser() {

        try {
            documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        try {
            startParsing();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    private void startParsing() throws IOException, SAXException, ParserConfigurationException {

        while(true){
            long startTime = System.currentTimeMillis();
            parseTubeLineInfoFromURL();
            //updateStationsProvider();

            logger.info("stations updated in "+(System.currentTimeMillis()-startTime)+" milliseconds");

            long timeSinceLastUpdate = 60000L - (System.currentTimeMillis()-startTime);



            try {
                Thread.sleep(timeSinceLastUpdate>=0 ? timeSinceLastUpdate:0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void parseTubeLineInfoFromURL() throws IOException, SAXException {

        InputStream stream = null;

        while(stream==null){
            try {
                stream = new URL(Shared.CIRCLELINE_PREDICTION_SUMMARY_URL).openStream();
            } catch(ConnectException e){
                logger.log(Level.SEVERE, "cannot connect to " + Shared.CIRCLELINE_PREDICTION_SUMMARY_URL);
            } catch (MalformedURLException e) {
                logger.log(Level.SEVERE, e.getMessage());
            } catch (IOException e) {
                logger.log(Level.SEVERE, e.getMessage());
            }
            if(stream==null){
                try {
                    Thread.sleep(30000);
                } catch (InterruptedException e1) {
                    logger.log(Level.SEVERE, e1.getMessage());
                }
            }
        }
        Document doc = documentBuilder.parse(stream);
        doc.getDocumentElement().normalize();
        parseStationsInfo(doc);

    }

    private void parseStationsInfo(Document doc) {

        NodeList stationsNodeList = doc.getDocumentElement().getChildNodes();
        String timeOfUpdate = null;

        for (int i = 0; i < stationsNodeList.getLength(); i++) {

            Node stationNode = stationsNodeList.item(i);
            if(stationNode.getNodeName().equals("Time")){
                timeOfUpdate = stationNode.getAttributes().getNamedItem("TimeStamp").getNodeValue();
            } else if(stationNode.getNodeName().equals("S")){
                Station station = new Station();
                station.setCode(stationNode.getAttributes().getNamedItem("Code").getNodeValue());
                station.setName(stationNode.getAttributes().getNamedItem("N").getNodeValue());
                station.setCurTime(timeOfUpdate);
                station.setPlatforms(parsePlatformsInfo(stationNode.getChildNodes()));

                stations.put(station.getCode(), station);
            }
        }
    }

    private List<Platform> parsePlatformsInfo(NodeList platformsNodeList) {

        List<Platform> platforms = new ArrayList<Platform>();

        for (int j = 0; j < platformsNodeList.getLength(); j++) {

            Node platformNode = platformsNodeList.item(j);
            if(platformNode.getNodeName().equals("P")){

                Platform p = new Platform();
                p.setName(platformNode.getAttributes().getNamedItem("N").getNodeValue());
                p.setTrains(parseCircleLineTrainsInfo(platformNode.getChildNodes()));
                platforms.add(p);
            }
        }

        return platforms;
    }

    private List<Train> parseCircleLineTrainsInfo(NodeList trainNodeList) {

        List<Train> trains = new ArrayList<Train>();

        for (int i = 0; i < trainNodeList.getLength(); i++) {

            Node trainNode = trainNodeList.item(i);
            if(trainNode.getNodeName().equals("T")
                    && trainNode.getAttributes().getNamedItem("D").getNodeValue().equals("702")){

                Train train = new Train();

                train.setTimeTo(trainNode.getAttributes().getNamedItem("C").getNodeValue());

                train.setLocation(trainNode.getAttributes().getNamedItem("L").getNodeValue());
                trains.add(train);
            }
        }

        return trains;
    }

    private String convertSecondsToMinutes(String timeToInSeconds) {

        long mins = TimeUnit.SECONDS.toMinutes(Integer.decode(timeToInSeconds));
        long secs = Integer.decode(timeToInSeconds)-mins*60;

        return (mins/10==0 ? "0"+mins : mins)+":"+(secs/10==0 ? "0"+secs : secs);

    }

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {

        new TubeInfoParser(null).startParsing();

    }

    public static void startParser(Map<String, Station> stations) {
        try {
            new TubeInfoParser(stations).start();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }
}
