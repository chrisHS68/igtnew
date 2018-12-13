import de.hsma.jens.controllers.FlightCustomerController;
import de.hsma.jens.controllers.PopulatorController;
import de.hsma.jens.models.FlightCustomer;
import de.hsma.jens.tools.FlightCustomerPopulator;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static de.hsma.jens.tools.FlightCustomerPopulator.populateCustomerAsList;

public class PopulatorTest {

   /* @Test
    public void createCustomer() {

        //Customer Anlegen
        List<FlightCustomer> flightCustomers;
        int anzahl = 1000;
        flightCustomers = populateCustomerAsList(anzahl);


        FlightCustomerController flightCustomerController = new FlightCustomerController();
        try {
            //In Datenbank schreiben und Zeit messen
            Long startTime = System.currentTimeMillis();
            flightCustomerController.createFlightCustomers(flightCustomers);
            Long endTime = System.currentTimeMillis();
            Long resultTime = endTime - startTime;

            System.out.println("Created " + anzahl + " Customers in: " + resultTime + "ms");

            //Customer Auslesen
            startTime = System.currentTimeMillis();
            List<FlightCustomer> readList = flightCustomerController.getAllFlightCustomers();
            endTime = System.currentTimeMillis();
            resultTime = endTime - startTime;

            System.out.println("Read " + readList.size() + " Customers in: " + resultTime + "ms");


            //Customer Updaten
            for (FlightCustomer c: readList) {
                c.setGesamtmeilen(1000);
            }
            startTime = System.currentTimeMillis();
            flightCustomerController.updateFlightCustomer(readList);
            endTime = System.currentTimeMillis();
            resultTime = endTime - startTime;

            System.out.println("Updated all Customers in " + resultTime + "ms");

          //Customer Löschen
            startTime = System.currentTimeMillis();
            flightCustomerController.deleteAllFlightCustomer();
            endTime = System.currentTimeMillis();
            resultTime = endTime - startTime;

            System.out.println("Deleted all Customers in " + resultTime + "ms");


        }catch(Exception e){
            e.printStackTrace();
        }

    }
*/
}
