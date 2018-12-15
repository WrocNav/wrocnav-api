package org.wrocnav.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wrocnav.model.Station;
import org.wrocnav.model.StopType;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class StationController
{
    private BufferedReader stationReader = null;
    private String location = "slupkiwspolrzedne.csv";
    private String line ="";
    private String linesplit = ";";

    @RequestMapping({"/stations"})
    public List<Station> stations() throws IOException
    {
        return this.getStations();
    }

//    private List<Station> getMockedStations() {
//        List<Station> stations = new ArrayList<>();
//        stations.add(new Station(23109L, "most Milenijny", 51.13651148D, 16.99927273D, StopType.BUS));
//        stations.add(new Station(2L, "Stalowa", 51.09969091D, 17.00149073D, StopType.BUS));
//        stations.add(new Station(3L, "Gądowianka", 51.11923502D, 16.97108638D, StopType.TRAM));
//        stations.add(new Station(4L, "Trzmielowicka", 51.14095766D, 16.85460787D, StopType.BUS));
//        stations.add(new Station(5L, "Złotnicka", 51.14328053D, 16.8890339D, StopType.BUS));
//        stations.add(new Station(6L, "Ramiszów", 51.1887837D, 17.10154452D, StopType.BUS));
//        stations.add(new Station(7L, "STADION WROCŁAW (Królewiecka)", 51.14502759D, 16.94791686D, StopType.BUS));
//        stations.add(new Station(8L, "Ojca Beyzyma", 51.09064679D, 16.9922862D, StopType.TRAM));
//        stations.add(new Station(9L, "Łosice", 51.21666368D, 17.20491645D, StopType.BUS_AND_TRAM));
//        stations.add(new Station(23109L, "Wilczyce", 51.13309646D, 17.14291716D, StopType.BUS));
//        return stations;
//    }

    private List<Station> getStations() throws IOException
    {
        List<Station> stations = new ArrayList<>();


        stationReader = new BufferedReader(new FileReader(location));
        String trashcan = stationReader.readLine();

        while((line = stationReader.readLine()) != null)
        {
            String[] station = line.split(linesplit);
//            System.out.println(station[0]);
            StopType type;
            if(Objects.equals(station[3], "3"))
            {
                type = StopType.BUS;
            }
            else if(Objects.equals(station[3], "0"))
            {
                type = StopType.TRAM;
            }
            else
            {
                type = StopType.BUS_AND_TRAM;
            }

            stations.add(new Station(Long.parseLong(station[2]),"AAA"+station[2],Double.parseDouble(station[1]),Double.parseDouble(station[0]),type));
        }

        return stations;
    }
}