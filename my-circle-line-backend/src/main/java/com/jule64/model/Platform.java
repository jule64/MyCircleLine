package com.jule64.model;

import java.util.List;

/**
* Created by julienmonnier on 21/03/2015.
*/
public class Platform {

    private String name;
    private List<Train> trains;

    public List<Train> getTrains() {
        return trains;
    }

    public void setTrains(List<Train> trains) {
        this.trains = trains;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
