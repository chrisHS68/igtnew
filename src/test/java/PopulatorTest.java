import de.hsma.jens.models.FlightCustomer;
import de.hsma.jens.tools.FlightCustomerPopulator;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static de.hsma.jens.tools.FlightCustomerPopulator.populateCustomerAsList;

public class PopulatorTest {
    @Test
    public void createCustomer() {
        FlightCustomer fa = new FlightCustomer();
     //   populateCustomerAsList(10);
        System.out.println(populateCustomerAsList(25000));

     //   CustomerAdressController customerAdressController = new CustomerAdressController();
        List<FlightCustomer> customer = new ArrayList<>();
        customer.add(fa);
     //   customerAdressController.createAdress(addresses);
     //   customerAdressController
     //           .getAllAdress()
     //           .forEach(customerAddress -> System.out.println(customerAddress.toString()));**/
    }

}
