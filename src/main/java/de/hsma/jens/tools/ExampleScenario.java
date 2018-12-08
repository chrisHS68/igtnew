package de.hsma.jens.tools;

import de.hsma.jens.models.Airport;
import de.hsma.jens.models.Flight;
import de.hsma.jens.models.Flightsegment;
import de.hsma.jens.models.Flugzeug;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExampleScenario {
    public static List<Flight> createExampleFlights() {

        List<Flight> flightList = new ArrayList<>();
        //Flüge Anlegen
        Flight flight1 = new Flight();
        Flight flight2 = new Flight();
        Flight flight3 = new Flight();
        Flight flight4 = new Flight();
        Flight flight5 = new Flight();
        Flight flight6 = new Flight();

        flight1.setId(1);
        flight1.setAbflugzeit(new Date(2018, 12, 11));
        flight1.setAnkuftszeit(new Date(2018, 12, 11));

        flight2.setId(2);
        flight2.setAbflugzeit(new Date(2018, 12, 11));
        flight2.setAnkuftszeit(new Date(2018, 12, 11));

        flight3.setId(3);
        flight3.setAbflugzeit(new Date(2018, 12, 11));
        flight3.setAnkuftszeit(new Date(2018, 12, 11));

        flight4.setId(4);
        flight4.setAbflugzeit(new Date(2018, 12, 11));
        flight4.setAnkuftszeit(new Date(2018, 12, 11));

        flight5.setId(5);
        flight5.setAbflugzeit(new Date(2018, 12, 11));
        flight5.setAnkuftszeit(new Date(2018, 12, 11));

        flight6.setId(6);
        flight6.setAbflugzeit(new Date(2018, 12, 11));
        flight6.setAnkuftszeit(new Date(2018, 12, 11));

        //Flugzeuge anlegen
        Flugzeug flugzeug1 = new Flugzeug();
        Flugzeug flugzeug2 = new Flugzeug();
        Flugzeug flugzeug3 = new Flugzeug();

        flugzeug1.setId(1);
        flugzeug1.setPreis_economy(249);
        flugzeug1.setPreis_ersteklasse(449);
        flugzeug1.setSitzplaetze_economy(3);
        flugzeug1.setSitzplaetze_ersteklasse(33);
        flugzeug1.setTypbezeichnung("Dornier-328-1");

        flugzeug2.setId(2);
        flugzeug2.setPreis_economy(269);
        flugzeug2.setPreis_ersteklasse(390);
        flugzeug2.setSitzplaetze_economy(3);
        flugzeug2.setSitzplaetze_ersteklasse(33);
        flugzeug2.setTypbezeichnung("Dornier-328-1");

        flugzeug3.setId(3);
        flugzeug3.setPreis_economy(190);
        flugzeug3.setPreis_ersteklasse(449);
        flugzeug3.setSitzplaetze_economy(3);
        flugzeug3.setSitzplaetze_ersteklasse(33);
        flugzeug3.setTypbezeichnung("Dornier-328-1");

        //Flughäfen anlegen

        Airport airportMannheim = new Airport();
        Airport airportSylt = new Airport();
        Airport airportHamburg = new Airport();
        Airport airportBerlin = new Airport();

        //Sylt, Hamburg, Berlin-Tegel
        airportMannheim.setId(1);
        airportMannheim.setName("Mannheim");
        airportMannheim.setLand("Deutschland");
        airportMannheim.setKuerzel("MHG");

        airportSylt.setId(2);
        airportSylt.setName("Sylt");
        airportSylt.setLand("Deutschland");
        airportSylt.setKuerzel("GWT");

        airportHamburg.setId(3);
        airportHamburg.setName("Hamburg");
        airportHamburg.setLand("Deutschland");
        airportHamburg.setKuerzel("HAM");

        airportBerlin.setId(4);
        airportBerlin.setName("Berlin-Tegel");
        airportBerlin.setLand("Deutschland");
        airportBerlin.setKuerzel("TXL");

        //Flightsegmente anlegen
        //Sylt 605km Hamburg 473km Berlin 478km
        Flightsegment flightsegment1 = new Flightsegment();
        Flightsegment flightsegment2 = new Flightsegment();
        Flightsegment flightsegment3 = new Flightsegment();
        Flightsegment flightsegment4 = new Flightsegment();
        Flightsegment flightsegment5 = new Flightsegment();
        Flightsegment flightsegment6 = new Flightsegment();

        flightsegment1.setId(1);
        flightsegment1.setName("Mannheim-Sylt");
        flightsegment1.setStrecke(605);


        flightsegment2.setId(2);
        flightsegment2.setName("Sylt-Mannheim");
        flightsegment2.setStrecke(605);


        flightsegment3.setId(3);
        flightsegment3.setName("Mannheim-Hamburg");
        flightsegment3.setStrecke(470);


        flightsegment4.setId(4);
        flightsegment4.setName("Hamburg-Mannheim");
        flightsegment4.setStrecke(470);


        flightsegment5.setId(5);
        flightsegment5.setName("Mannheim-Berlin");
        flightsegment5.setStrecke(480);


        flightsegment6.setId(6);
        flightsegment6.setName("Berlin-Mannheim");
        flightsegment6.setStrecke(480);


/*
      Beziehungen anlegen
*/

        //Flightsegment <-> Airport
        flightsegment1.setAbflughafen(airportMannheim);
        flightsegment1.setZielflughafen(airportSylt);

        flightsegment2.setAbflughafen(airportSylt);
        flightsegment2.setZielflughafen(airportMannheim);

        flightsegment3.setAbflughafen(airportMannheim);
        flightsegment3.setZielflughafen(airportHamburg);

        flightsegment4.setAbflughafen(airportHamburg);
        flightsegment4.setZielflughafen(airportMannheim);

        flightsegment5.setAbflughafen(airportMannheim);
        flightsegment5.setZielflughafen(airportBerlin);

        flightsegment6.setAbflughafen(airportBerlin);
        flightsegment6.setZielflughafen(airportMannheim);

        //Flightsegment <-> Flight
        ArrayList<Flightsegment> flightsegments1 = new ArrayList<>();
        flightsegments1.add(flightsegment1);
        flight1.setSegments(flightsegments1);

        ArrayList<Flightsegment> flightsegments2 = new ArrayList<>();
        flightsegments2.add(flightsegment2);
        flight2.setSegments(flightsegments2);

        ArrayList<Flightsegment> flightsegments3 = new ArrayList<>();
        flightsegments3.add(flightsegment3);
        flight3.setSegments(flightsegments3);

        ArrayList<Flightsegment> flightsegments4 = new ArrayList<>();
        flightsegments4.add(flightsegment4);
        flight4.setSegments(flightsegments4);

        ArrayList<Flightsegment> flightsegments5 = new ArrayList<>();
        flightsegments5.add(flightsegment5);
        flight5.setSegments(flightsegments5);

        ArrayList<Flightsegment> flightsegments6 = new ArrayList<>();
        flightsegments6.add(flightsegment6);
        flight6.setSegments(flightsegments6);

        //Flugzeug <-> Flight
        flight1.setFlugzeug(flugzeug1);
        flight2.setFlugzeug(flugzeug1);

        flight3.setFlugzeug(flugzeug2);
        flight4.setFlugzeug(flugzeug2);

        flight5.setFlugzeug(flugzeug3);
        flight6.setFlugzeug(flugzeug3);

        flightList.add(flight1);
        flightList.add(flight2);
        flightList.add(flight3);
        flightList.add(flight4);
        flightList.add(flight5);
        flightList.add(flight6);


        return flightList;
    }
}
