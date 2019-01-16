package org.wrocnav.model;

import java.util.List;

public class Tram {
    private int number;
    private int station;
    private int version;
    private List<TimeAtPoint> times;

    public Tram(int number, int station, int version, List<TimeAtPoint> times) {
        this.number = number;
        this.station = station;
        this.version = version;
        this.times = times;
    }

    public int getNumber() {
        return number;
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
