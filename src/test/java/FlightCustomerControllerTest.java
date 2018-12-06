
import de.hsma.jens.models.FlightCustomer;
import de.hsma.jens.models.Status;

import org.junit.After;
import org.junit.Test;

import de.hsma.jens.controllers.FlightCustomerController;
import java.util.ArrayList;
import java.util.List;

public class FlightCustomerControllerTest {
    /**
     * @Before public void setUp() throws Exception {
     * <p>
     * FlightCustomerController flightCustomerController = new FlightCustomerController();
     * <p>
     * //flightCustomerController.deleteAllCustomer();
     * //flightCustomerController.createCustomers();
     * <p>
     * <p>
     * }
     **/
    @Test
    public void createCustomersTest() {

        FlightCustomer fc = new FlightCustomer();
        fc.setJahresmeilen(0);
        fc.setGesamtmeilen(0);
        fc.setCustomerStatus(Status.NONE);

        FlightCustomerController flightCustomerController = new FlightCustomerController();
        List<FlightCustomer> customers = new ArrayList<>();
        customers.add(fc);
        flightCustomerController.createFlightCustomers(customers);
        flightCustomerController.getAllFlightCustomers().forEach(flightCustomer -> System.out.println(flightCustomer.toString()));

    }
 @After
 public void tearDown() throws Exception {
 FlightCustomerController flightCustomerController = new FlightCustomerController();
 //custController.deleteAllCustomer();
 }

}




