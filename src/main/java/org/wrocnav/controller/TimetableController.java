package org.wrocnav.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wrocnav.model.Tram;
import org.wrocnav.utils.xml.TimetableXMLParser;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TimetableController {

    @RequestMapping("/timetables/{id}")
    public List<Tram> timetables(@PathVariable String id) {

        if(id.matches("^[a-zA-Z0-9]*$")) {
            return getTimetable(id);
        }

        return new ArrayList<>();
    }

    private List<Tram> getTimetable(String id) {
        id = adjustLengthToProperFileName(id);

        return TimetableXMLParser.parseToList(
                "open_data_resources/XML-rozkladyjazdy/" + id + "/" + id + ".xml");
    }

    private String adjustLengthToProperFileName(String id) {
        id = id.replaceFirst("^0*", "");

        StringBuilder idBuilder = new StringBuilder(id);

        while(idBuilder.length() < 4) {
            idBuilder.insert(0, "0");
        }

        return idBuilder.toString();
    }
}
