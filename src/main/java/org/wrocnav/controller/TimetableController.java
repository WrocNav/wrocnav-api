package org.wrocnav.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wrocnav.model.Tram;
import org.wrocnav.utils.xml.TimetableXMLParser;

import java.util.List;

@RestController
public class TimetableController {

    @RequestMapping("/timetables")
    public List<Tram> timetables() {
        return getTimetable();
    }

    private List<Tram> getTimetable() {
        return TimetableXMLParser.parseToList("0001.xml");
    }
}
