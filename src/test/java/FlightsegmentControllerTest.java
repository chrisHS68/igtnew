import de.hsma.jens.controllers.FlightsegmentController;
import de.hsma.jens.models.Airport;
import de.hsma.jens.models.Flight;
import de.hsma.jens.models.Flightsegment;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class FlightsegmentControllerTest {
/**   @Test
    public void createFlightsegmentControllerTest() {
        Flightsegment fs = new Flightsegment();

        Airport ar = new Airport();
        Airport ar2 = new Airport();
        List<Flight> fl = new ArrayList<>();

        fs.setAbflughafen(ar);
        fs.setFlights(fl);
        fs.setId(1);
        fs.setName("testName");
        fs.setStrecke(600);
        fs.setZielflughafen(ar2);

        FlightsegmentController flightsegmentController = new FlightsegmentController();
        List<Flightsegment> flightsegment = new ArrayList<>();
        flightsegment.add(fs);
        flightsegmentController.createFlightSegment(flightsegment);
        flightsegmentController
                .getAllFlightsegments()
                .forEach(flightsegment1 -> System.out.println(flightsegment1.toString()));
    }
/**/
}
