package org.wrocnav.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wrocnav.model.Station;
import org.wrocnav.model.StopType;
import org.wrocnav.utils.xml.StationXMLParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class StationController {


    @RequestMapping("/stations")
    public List<Station> stations() throws IOException {
        return this.getStations();
    }

    private List<Station> getStations() throws IOException {
        return StationXMLParser.parseToList();
    }
}