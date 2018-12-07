import de.hsma.jens.models.*;
import de.hsma.jens.tools.Config;
import org.apache.log4j.Logger;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import de.hsma.jens.controllers.FlightController;
import de.hsma.jens.models.FlightCustomer;
import de.hsma.jens.models.Status;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FlightControllerTest {

    /**
     * @Before public void setUp() throws Exception {
     * <p>
     * FlightController flightController = new FlightController();
     * <p>
     * //flightController.deleteAllFlight();
     * //flightController.createFlight();
     * <p>
     * <p>
     * }
     **/
    /**
    @Test
    public void createFlightTest() {

        Flight fl = new Flight();

        Date AbflugDay = new Date(2019, 01, 01);
        Date AnkunftDay = new Date(2019, 01, 14);

         fl.setAbflugzeit(AbflugDay);
         fl.setAnkuftszeit(AnkunftDay);

         List<CustomerAddress> customerAddresses = new ArrayList<>();
         CustomerAddress adress1 = new CustomerAddress();

         List<Flight> flights = new ArrayList<>();
         Flight flight1 = new Flight();

         List<PhoneType> phone = new ArrayList<>();
         PhoneType phone1 = new PhoneType();

         List<Flightsegment> flightSegment = new ArrayList<>();
         Flightsegment flightSegment1 = new Flightsegment();

         List<Flugzeug> flugzeug =  new ArrayList<>();
          Flugzeug flugzeug1 = new Flugzeug();

          List<FlightCustomer> customers =  new ArrayList<>();
          FlightCustomer c1 = new FlightCustomer();

         c1.setJahresmeilen(0);
         c1.setGesamtmeilen(0);
         c1.setCustomerAddresses(customerAddresses);
         c1.setCustomerStatus(Status.NONE);
         c1.setId(1);
         c1.setFlights(flights);
         c1.setPhones(phone);

         customers.add(c1);

         fl.setCustomer(customers);

         flugzeug.add(flugzeug1);
         fl.setFlugzeug(flugzeug1);

         flightSegment.add(flightSegment1);
         fl.setSegments(flightSegment);

         fl.setId(1);




        FlightController flightController = new FlightController();
        List<Flight> flightsList = new ArrayList<>();
        flights.add(fl);
        flightController.createFlight(flights);
        flightController.getAllFlight().forEach(flight -> System.out.println(flight.toString()));

    }
    /**/
/**
 @After public void tearDown() throws Exception {
 FlightController flightController = new FlightController();
 //flController.deleteAllFlights();
 }
 **/
/**/
}