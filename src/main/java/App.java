import de.hsma.jens.controllers.AirportController;
import de.hsma.jens.controllers.FlightController;
import de.hsma.jens.controllers.FlightCustomerController;
import de.hsma.jens.models.Flight;
import de.hsma.jens.models.FlightCustomer;
import org.apache.lucene.search.similarities.Lambda;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        FlightCustomer flightCustomer = new FlightCustomer();
//        FlightCustomerController flightCustomerController = new FlightCustomerController();

        //Abflughafen auswählen
        System.out.println("Abflughafen wählen");
//        AirportController airportController = new AirportController();
//        airportController.getAllAirports().forEach(airport -> System.out.println(airport.toString()));
        Scanner scanner = new Scanner(System. in);
        Integer abflugEingabe = Integer.parseInt(scanner.nextLine());
        System.out.println("Gewählter Abflughafen: "+ abflugEingabe);

        //Zielflughafen auswählen
        System.out.println("Zielflughafen wählen");
//        airportController.getAllAirports().forEach(airport -> System.out.println(airport.toString()));
        Integer zielEingabe = Integer.parseInt(scanner.nextLine());
        System.out.println("Gewählter Zielflughafen: "+ zielEingabe);

        //Abflugdatum wählen
        System.out.println("Abflugdatum wählen");
        String abflugdatum = scanner.nextLine();
        System.out.println("Gewähltes Datum: "+abflugdatum);

        //Rückflugdatum wählen
        System.out.println("Rückflugdatum wählen");
        String rückflugdatum = scanner.nextLine();
        System.out.println("Gewähltes Datum: "+rückflugdatum);

        //Flüge anzeigen
       // FlightController flightController = new FlightController();
       // List <Flight> possibleFlights= flightController.getAllFlight();
        System.out.println("Kein Flug verfügbar");
    }

}
