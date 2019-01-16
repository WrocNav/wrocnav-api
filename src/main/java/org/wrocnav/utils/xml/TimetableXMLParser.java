package org.wrocnav.utils.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.wrocnav.model.TimeAtPoint;
import org.wrocnav.model.Tram;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TimetableXMLParser {

    public static List<Tram> parseToList(String filename) {
        int linia;
        int version;
        String nazwa;
        int idstacji;
        String typeoftram;
        char type;

        List<Tram> timetables = new ArrayList<>();

        File xmlFile = new File(filename);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = null;
        try {
            docBuilder = dbFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Document doc = null;
        try {
            doc = docBuilder.parse(xmlFile);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // begin parsing xml
        doc.getDocumentElement().normalize();
        System.out.println(doc.getDocumentElement().getNodeName());

        // list lines
        NodeList tramid = doc.getElementsByTagName("linia");
        Node temp = tramid.item(0);
        Element elem = (Element) temp;

        // get line id
        linia = Integer.parseInt(elem.getAttribute("nazwa"));

        Tram tram;
        //get version list
        NodeList versionList = doc.getElementsByTagName("wariant");
        for (int war = 0; war < versionList.getLength(); war++) {
            Node nodevers = versionList.item(war);
            Element verElem = (Element) nodevers;

            //get version
            version = Integer.parseInt(verElem.getAttribute("id"));
            System.out.println(version);
            NodeList stationList = ((Element) nodevers).getElementsByTagName("przystanek");
            for (int stat = 0; stat < stationList.getLength(); stat++) {
                Node nodestation = stationList.item(stat);
                Element statElem = (Element) nodestation;

                // get station id and name
                idstacji = Integer.parseInt(statElem.getAttribute("id"));
                nazwa = statElem.getAttribute("nazwa");

                List<TimeAtPoint> tramtime = new ArrayList<>();

                NodeList timeList = ((Element) nodestation).getElementsByTagName("dzien");
                for (int day = 0; day < timeList.getLength(); day++) {
                    Node nodeday = timeList.item(day);
                    Element dayElem = (Element) nodeday;

                    if (dayElem.getAttribute("nazwa") == "W dni robocze") {
                        type = 'D';
                    } else if (dayElem.getAttribute("nazwa") == "Sobota") {
                        type = 'S';
                    } else {
                        type = 'N';
                    }

                    NodeList hourList = ((Element) nodeday).getElementsByTagName("godz");
                    for (int hr = 0; hr < hourList.getLength(); hr++) {
                        Node nodehour = hourList.item(hr);
                        Element hourElem = (Element) nodehour;
                        int hour = Integer.parseInt(hourElem.getAttribute("h"));

                        NodeList minList = ((Element) nodehour).getElementsByTagName("min");
                        for (int mi = 0; mi < minList.getLength(); mi++) {
                            Node nodemin = minList.item(mi);
                            Element miElem = (Element) nodemin;
                            int min = Integer.parseInt(miElem.getAttribute("m"));
                            typeoftram = miElem.getAttribute("ozn");
                            TimeAtPoint currentTram = new TimeAtPoint(hour, min, typeoftram, type);
                            tramtime.add(currentTram);

                        }

                    }

                }
                tram = new Tram(linia, idstacji, version, tramtime);
                timetables.add(tram);
            }

        }
        return timetables;
    }
}
