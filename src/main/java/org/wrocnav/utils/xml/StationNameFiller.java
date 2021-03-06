package org.wrocnav.utils.xml;

import org.wrocnav.model.Station;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class StationNameFiller {

    private static String STATION_ID_TO_NAME_FILE_PATH = "open_data_resources/mappedStopIdToName.txt";

    public static List<Station> fillStationNames(List<Station> stations) {
        try {
            Map<Long, String> stationIdToNameMap = getStationIdToNameFromFile(STATION_ID_TO_NAME_FILE_PATH);

            for (Iterator<Station> iterator = stations.iterator(); iterator.hasNext();) {
                Station station = iterator.next();
                String name = stationIdToNameMap.get(station.getId());

                if(name == null || name.equals("")) {
                    iterator.remove();
                } else {
                    station.setName(name);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stations;
    }

    private static Map<Long, String> getStationIdToNameFromFile(String path) throws IOException {
        Map<Long, String> stationIdToNameMap = new HashMap<>();
        FileReader fileReader = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String textLine = bufferedReader.readLine();
        do {
            String[] line = textLine.split(";");
            stationIdToNameMap.put(Long.parseLong(line[0]), line[1]);
            textLine = bufferedReader.readLine();

        } while(textLine != null);

        bufferedReader.close();

        return stationIdToNameMap;
    }
}
