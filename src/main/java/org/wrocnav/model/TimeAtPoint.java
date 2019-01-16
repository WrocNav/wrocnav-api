package org.wrocnav.model;

public class TimeAtPoint {
    private int hour;
    private int minutes;
    private char daysType;
    private String typeOfTram;

    public TimeAtPoint(int hour, int minutes, String typeoftram, char daystype) {
        this.hour = hour;
        this.minutes = minutes;
        this.daysType = daystype;
        this.typeOfTram = typeoftram;
    }

    public int getHour() {
        return hour;
    }

    public int getMinutes() {
        return minutes;
    }

    public char getDaysType() {
        return daysType;
    }

    public String getTypeOfTram() {
        return typeOfTram;
    }
}
