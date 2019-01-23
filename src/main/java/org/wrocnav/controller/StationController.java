package org.wrocnav.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wrocnav.model.Station;
import org.wrocnav.utils.xml.StationNameFiller;
import org.wrocnav.utils.xml.StationXMLParser;

import java.util.List;

@RestController
public class StationController {

    @RequestMapping("/stations")
    public List<Station> stations() {
        List<Station> stationsWithoutProperName = StationXMLParser.parseToList();
        return StationNameFiller.fillStationNames(stationsWithoutProperName);
    }
}