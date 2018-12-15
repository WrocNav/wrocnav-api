package org.wrocnav.model;

public class TimeAtPoint
{
    private int hour;
    private int minutes;
    private char daystype;
    private String typeoftram;

    public TimeAtPoint(int hour, int minutes,String typeoftram, char daystype)
    {
        this.hour = hour;
        this.minutes = minutes;
        this.daystype = daystype;
        this.typeoftram = typeoftram;
    }

    public int getHour()
    {
        return hour;
    }
    public int getMinutes()
    {
        return minutes;
    }
    public char getDaystype()
    {
        return daystype;
    }
    public String getTypeoftram()
    {
        return typeoftram;
    }
}
