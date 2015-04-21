package com.jule64.model;

import java.util.List;

public class Station {

    private String name;
    private String code;
    private String curTime;
    private List<Platform> platforms;

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getCurTime() {
        return curTime;
    }

    public List<Platform> getPlatforms() {
        return platforms;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setCurTime(String curTime) {
        this.curTime = curTime;
    }

    public void setPlatforms(List<Platform> platforms) {
        this.platforms = platforms;
    }


}
