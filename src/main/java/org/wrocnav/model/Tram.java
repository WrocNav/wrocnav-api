package org.wrocnav.model;

import java.util.List;

public class Tram {
    private String line;
    private int station;
    private int version;
    private List<TimeAtPoint> times;

    public Tram(String number, int station, int version, List<TimeAtPoint> times) {
        this.line = number;
        this.station = station;
        this.version = version;
        this.times = times;
    }

    public String getLine() {
        return line;
    }

    public int getStation() {
        return station;
    }

    public int getVersion() {
        return version;
    }

    public List<TimeAtPoint> getTimes() {
        return times;
    }
}
