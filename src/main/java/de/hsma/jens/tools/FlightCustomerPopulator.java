package de.hsma.jens.tools;

import de.hsma.jens.models.*;

import java.util.Date;
import java.util.Random;

import java.util.ArrayList;
import java.util.List;


public class FlightCustomerPopulator {

    // Populater for customerList
    public static List<FlightCustomer> populateCustomerAsList(int i) {

        List<FlightCustomer> fcList = new ArrayList<FlightCustomer>();

        List<PhoneType> ptList = new ArrayList<PhoneType>();
        List<CustomerAddress> caList = new ArrayList<CustomerAddress>();
        List<Flight> flList = new ArrayList<Flight>();

        int FC_ID = 0;
        String FC_NAME = ("Testname");
        int FC_GESMANTMELEN = 0;
        int FC_JAHRESMEILEN = 0;
        List FC_PHONE = ptList;
        List FC_FLIGHTS = flList;
        Status FC_STATUS = Status.NONE;
        String FC_ADRESS = ("Teststrasse" + i);


        try {

            for (i = 1; i <= 100; i++) {

                FlightCustomer myFlightCustomer = new FlightCustomer();

                try {// Set parameter

                    myFlightCustomer.setId(i);
                    myFlightCustomer.setName("Name" + i);
                    myFlightCustomer.setPhones(ptList);
                    myFlightCustomer.setFlights(flList);
                    myFlightCustomer.setCustomerStatus(FC_STATUS);
                   // myFlightCustomer.setCustomerAddress(FC_ADRESS);
                    myFlightCustomer.setGesamtmeilen(FC_GESMANTMELEN);
                    myFlightCustomer.setJahresmeilen(FC_JAHRESMEILEN);


                    fcList.add(myFlightCustomer);


                } catch (java.lang.Exception ex) {
                    System.err.println("Unable to populate FLIGHTCUSTOMER table");
                    System.out.println("FC_ID=" + i + " FC_NAME=" + FC_NAME +
                            " FC_PHONE=" + FC_PHONE + " FC_FLIGHTS="
                            + FC_FLIGHTS + //*" FC_STATUS=" + FC_STATUS +
                            " FC_ADRESS=" + FC_ADRESS + " FC_GESAMTMEILEN= " + FC_GESMANTMELEN +
                            " FC_JAHRESMEILEN=" + FC_JAHRESMEILEN);
                    ex.printStackTrace();
                    System.exit(1);
                }
            }
            System.out.print("\n");
            ////con.commit();
        } catch (java.lang.Exception ex) {
            System.err.println("Unable to populate FLIGHTCUSTOMER table");
            ex.printStackTrace();
            System.exit(1);
        }

        return fcList;

    }

    //Populator for PhoneType

    public static List<PhoneType> populatePhoneTypeAsList(int i) {

        List<PhoneType> ptList = new ArrayList<PhoneType>();

        Random rand = new Random();
        Integer n = rand.nextInt(12) + 1;


        for (i = 1; i <= 100; i++) {

                PhoneType myPhoneType = new PhoneType();

                // Set parameter

                    myPhoneType.setId(i);
                    myPhoneType.setNumber(n);

                    ptList.add(myPhoneType);
                }

            return ptList;

    }

    //Populator for Flights

    public static List<Flight> populateFlightsAsList(int i) {

        List<Flight> flList = new ArrayList<Flight>();
        List<Flugzeug> flugzeugList = new ArrayList<Flugzeug>();
        Flugzeug flugzeug = new Flugzeug();


        for (i = 1; i <= 100; i++) {

            Flight myFlight = new Flight();

            // Set parameter

            myFlight.setId(i);
            myFlight.setSegments(new ArrayList<>());
            myFlight.setFlugzeug(flugzeug);
            myFlight.setAnkuftszeit(new Date(2019, 01, 01));
            myFlight.setAbflugzeit(new Date(2019, 01, 14));


            flList.add(myFlight);
        }

        return flList;

    }

};

