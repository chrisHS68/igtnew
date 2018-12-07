import de.hsma.jens.controllers.AirportController;
import de.hsma.jens.models.Airport;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class AirportControllerTest {
/**    @Test
    public void createFlugzeugControllerTest() {
        Airport ar = new Airport();

        ar.setId(1);
        ar.setKuerzel("FRA");
        ar.setLand("DE");
        ar.setName("FFM Airport");


        AirportController airportController = new AirportController();
        List<Airport> airport = new ArrayList<>();
        airport.add(ar);
        airportController.createAirport(airport);
        airportController
                .getAllAirports()
                .forEach(airport1 -> System.out.println(airport1.toString()));
    }
/**/
}
