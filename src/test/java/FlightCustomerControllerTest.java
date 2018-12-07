
import de.hsma.jens.controllers.CustomerAdressController;
import de.hsma.jens.controllers.PhoneTypeController;
import de.hsma.jens.models.CustomerAddress;
import de.hsma.jens.models.FlightCustomer;
import de.hsma.jens.models.PhoneType;
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
        //Customer Anlegen
        FlightCustomer fc1 = new FlightCustomer();
        fc1.setId(2);
        fc1.setJahresmeilen(0);
        fc1.setGesamtmeilen(0);
        fc1.setCustomerStatus(Status.NONE);


        //Adresse für Customer anlegen
        CustomerAddress ca1 = new CustomerAddress();
        ca1.setHausnummer("12b");
        ca1.setOrt("Mannheim");
        ca1.setPlz("68305");
        ca1.setStrasse("Teststraße");
        ca1.setId(2);

        //Adresse mit Customer verknüpfen
        fc1.setCustomerAddress(ca1);

        //Telefon für Customer anlegen
        PhoneType pt1 = new PhoneType();
        pt1.setNumber(new Long("12345567"));
        pt1.setId(2);


        //Listen für many-relations erzeugen
        List <PhoneType> phoneTypeList = new ArrayList<>();
        phoneTypeList.add(pt1);
        List<FlightCustomer> flightCustomerList = new ArrayList<>();
        flightCustomerList.add(fc1);

        //Verknüpfungen erstellen
        flightCustomerList.get(0).setPhones(phoneTypeList);
        phoneTypeList.get(0).setCustomer(flightCustomerList);


        //Transaktion Ausführen
     /*   PhoneTypeController phoneTypeController = new PhoneTypeController();
        phoneTypeController.createPhoneType(phoneTypeList);*/
        FlightCustomerController flightCustomerController = new FlightCustomerController();
        flightCustomerController.createFlightCustomers(flightCustomerList);
        flightCustomerController.getAllFlightCustomers().forEach(flightCustomer -> System.out.println(flightCustomer.toString()));

    }
 @After
 public void tearDown() throws Exception {
 FlightCustomerController flightCustomerController = new FlightCustomerController();
 //custController.deleteAllCustomer();
 }

}




