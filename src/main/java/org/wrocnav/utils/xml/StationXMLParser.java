package org.wrocnav.utils.xml;

import org.wrocnav.model.Station;
import org.wrocnav.model.StopType;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StationXMLParser {

    private static String STATION_LOCATION_CSV_PATH = "open_data_resources/slupkiwspolrzedne.csv";

    public static List<Station> parseToList() {
        BufferedReader stationReader = null;
        String line;
        List<Station> stations = new ArrayList<>();

        try {
            stationReader = new BufferedReader(new FileReader(STATION_LOCATION_CSV_PATH));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            stationReader.readLine();

            while ((line = stationReader.readLine()) != null) {
                String[] station = line.split(";");
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
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stations;
    }
}
