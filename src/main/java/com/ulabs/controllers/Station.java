package com.ulabs.controllers;

import java.util.List;

public class Station {

    private final long id;
    private final String content;
    private String name;
    private List<String> directions;


    public Station(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
