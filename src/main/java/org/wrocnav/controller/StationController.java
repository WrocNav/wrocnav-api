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
public class StationController {
    private BufferedReader stationReader = null;
    private String location = "slupkiwspolrzedne.csv";
    private String line = "";
    private String linesplit = ";";

    @RequestMapping("/stations")
    public List<Station> stations() throws IOException {
        return this.getStations();
    }

    private List<Station> getStations() throws IOException {
        List<Station> stations = new ArrayList<>();


        stationReader = new BufferedReader(new FileReader(location));
        String trashcan = stationReader.readLine();

        while ((line = stationReader.readLine()) != null) {
            String[] station = line.split(linesplit);
            StopType type;

            if (Objects.equals(station[3], "3")) {
                type = StopType.BUS;
            } else if (Objects.equals(station[3], "0")) {
                type = StopType.TRAM;
            } else {
                type = StopType.BUS_AND_TRAM;
            }

            stations.add(new Station(Long.parseLong(station[2]), "AAA" + station[2], Double.parseDouble(station[1]), Double.parseDouble(station[0]), type));
        }

        return stations;
    }
}