package de.hsma.jens.tools;

import de.hsma.jens.models.*;

import java.util.Date;
import java.util.Random;

import java.util.ArrayList;
import java.util.List;


public class FlightCustomerPopulator {

    // Populater for customerList
    public static List<FlightCustomer> populateCustomerAsList(int anzahl) {

        List<FlightCustomer> fcList = new ArrayList<FlightCustomer>();

        int FC_GESMANTMELEN = 10000;
        int FC_JAHRESMEILEN = 100;
        Status FC_STATUS = Status.NONE;


        for (int i = 1; i <= anzahl; i++) {
            try {// Set parameter

                //Adresse Anlegen
                CustomerAddress customerAddress = new CustomerAddress();
                customerAddress.setId(i);
                customerAddress.setOrt("Testort");
                customerAddress.setPlz("68" + i);
                customerAddress.setHausnummer("" + i);
                customerAddress.setStrasse("Teststraße");

                //Phones Anlegen
                List<PhoneType> phoneTypeList = new ArrayList<>();
                PhoneType phoneType = new PhoneType();
                phoneType.setId(i);
                phoneType.setNumber(new Long("06217445"));
                phoneTypeList.add(phoneType);

                //Kunden Anlegen
                FlightCustomer myFlightCustomer = new FlightCustomer();
                myFlightCustomer.setId(i);
                myFlightCustomer.setName("Name" + i);
                myFlightCustomer.setCustomerStatus(FC_STATUS);
                myFlightCustomer.setGesamtmeilen(FC_GESMANTMELEN);
                myFlightCustomer.setJahresmeilen(FC_JAHRESMEILEN);

                //Beziehungen anlegen
                myFlightCustomer.setCustomerAddress(customerAddress);
                myFlightCustomer.setPhones(phoneTypeList);


                fcList.add(myFlightCustomer);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return fcList;
    }
}
