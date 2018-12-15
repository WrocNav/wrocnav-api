package org.wrocnav.model;

public class Station {
    private final long id;
    private String name;
    private final double latitude;
    private final double longitude;
    private final StopType type;

    public Station(long id, String name, double latitude, double longitude, StopType type) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.type = type;
    }

    public void changeName(String name)
    {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public StopType getType() {
        return type;
    }
}
