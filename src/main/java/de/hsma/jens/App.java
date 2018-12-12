package de.hsma.jens;

import de.hsma.jens.controllers.*;
import de.hsma.jens.models.*;
import de.hsma.jens.tools.ExampleScenario;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        //Daten der Fluggeselschaft anlegen
        List<Flight> flightList = ExampleScenario.createExampleFlights();
        FlightController flightController = new FlightController();
        flightController.createFlight(flightList);
        FlightCustomerController fcController = new FlightCustomerController();

        int i = 1;
        //Dauerschleife für Buchungen
        while (true) {
            //Leeren Kunden anlegen
            FlightCustomer flightCustomer = new FlightCustomer();
            flightCustomer.setId(i);
            flightCustomer.setGesamtmeilen(0);
            flightCustomer.setCustomerStatus(Status.NONE);
            flightCustomer.setJahresmeilen(0);
            flightCustomer.setGesamtmeilen(0);
            List<FlightCustomer> fcList = new ArrayList<FlightCustomer>();
            fcList.add(flightCustomer);
            fcController.createFlightCustomers(fcList);


            //Alle vefügbaren Fluhäfen abrufen
            AirportController flugzeugController = new AirportController();
            List <Airport> airportList = new ArrayList<>();
            airportList = flugzeugController.getAllAirports();

            //Abflughafen auswählen
            System.out.println("Abflughafen wählen");
            for (Airport ap:airportList) {
                System.out.println(ap.getId() + ": " + ap.getName());
            }
            Scanner scanner = new Scanner(System.in);
            Integer abflugEingabe = Integer.parseInt(scanner.nextLine());
            System.out.println("Gewählter Abflughafen: " + abflugEingabe);

            //Zielflughafen auswählen
            System.out.println("Zielflughafen wählen");
            for (Airport ap:airportList) {
                if(abflugEingabe != ap.getId())
                System.out.println(ap.getId() + ": " + ap.getName());
            }
            Integer zielEingabe = Integer.parseInt(scanner.nextLine());
            System.out.println("Gewählter Zielflughafen: " + zielEingabe);

            //Abflugdatum wählen
            System.out.println("Abflugdatum wählen (dd.MM.yyyy)");
            String abflugdatumEingabe = scanner.nextLine();
            Date abflugdatum = createDate(abflugdatumEingabe);

            System.out.println("Gewähltes Datum: " + abflugdatum.toString());

            //Rückflugdatum wählen
/*
            System.out.println("Rückflugdatum wählen (dd.MM.yyyy)");
            String rückflugdatumEingabe = scanner.nextLine();
            Date rückflugdatum = createDate(rückflugdatumEingabe);

            System.out.println("Gewähltes Datum: " + rückflugdatum.toString());
*/

            //Flüge anzeigen
            List<Flight> möglicheFlüge = new ArrayList<>();
            möglicheFlüge = flightController.getAllFlight();
            for (Flight f: flightList) {
                if(f.getSegments().get(0).getAbflughafen().getId() == abflugEingabe &&
                        f.getSegments().get(0).getZielflughafen().getId() == zielEingabe){
                    möglicheFlüge.add(f);
                    System.out.println(f.getSegments().get(0).getName());
                }
            }
            if (möglicheFlüge.size() == 0){
                System.out.println("Kein Flug verfügbar");
            }


            //Strecke Berechnen
            int strecke = 0;
            strecke = möglicheFlüge.get(0).getSegments().get(0).getStrecke();
            System.out.println("Die Strecke beträgt: " + strecke + "km");

            //Abflug und Ankunftszeit anzeigen
            Date abflugzeit = möglicheFlüge.get(0).getAbflugzeit();
            Date ankunftzeit = möglicheFlüge.get(0).getAnkuftszeit();
            System.out.println("Ablugzeit: " + abflugzeit);
            System.out.println("Ankuftzeit: " + ankunftzeit);

            //Sitzplatz Art wählen
            System.out.println("Flugklasse wählen:");
            System.out.println("(1)Erste Klasse");
            System.out.println("(2)Economy");
            int auswahlSitzklasse = 0;
            auswahlSitzklasse = Integer.parseInt(scanner.nextLine());

            //Kundendaten eingeben
            FlightCustomerController flightCustomerController = new FlightCustomerController();
            CustomerAdressController customerAdressController = new CustomerAdressController();
            PhoneTypeController phoneTypeController = new PhoneTypeController();

            //flightCustomer.setId(i);

            CustomerAddress customerAddress = new CustomerAddress();
            customerAddress.setId(i);
            System.out.println("Name eingeben");
            flightCustomer.setName(scanner.nextLine());
            System.out.println("Straße eingeben: ");
            customerAddress.setStrasse(scanner.nextLine());
            System.out.println("Hausnummer eingeben: ");
            customerAddress.setHausnummer(scanner.nextLine());
            System.out.println("PLZ eingeben: ");
            customerAddress.setPlz(scanner.nextLine());
            System.out.println("Ort eigeben: ");
            customerAddress.setOrt(scanner.nextLine());
            customerAddress.setCustomer(flightCustomer);

            PhoneType phone = new PhoneType();
            phone.setId(i);
            System.out.println("Telefonnummer eingeben:");
            int phonenumber = Integer.parseInt(scanner.nextLine());
            phone.setNumber(new Long(phonenumber));

            List<PhoneType> phones = new ArrayList<>();
            phones.add(phone);
            phoneTypeController.createPhoneType(phones);
            flightCustomer.setPhones(phones);

            List<CustomerAddress> customerAddresses = new ArrayList<>();
            customerAddresses.add(customerAddress);

            flightCustomer.setCustomerAddress(customerAddress);

            //Kundenstatus anzeigen
            Status kundestatus = Status.NONE;
            System.out.println("Aktueller Kundenstatus: " + kundestatus);

            //Preisermittlung starten
            Integer preis = 0;

            if(auswahlSitzklasse == 1){
                preis = möglicheFlüge.get(0).getFlugzeug().getPreis_ersteklasse();
            }else if(auswahlSitzklasse == 2){
                preis = möglicheFlüge.get(0).getFlugzeug().getPreis_economy();
            }
            System.out.println("Flugpreis" + preis + "€");

            //Flug Buchen
            System.out.println("Flug buchen? ");
            System.out.println("1: JA ");
            System.out.println("2: NEIN");
            int buchungswahl = 0;
            buchungswahl = Integer.parseInt(scanner.nextLine());
            if (buchungswahl == 1) {
                System.out.println("Flug gebucht");
                flightCustomer.setFlights(möglicheFlüge);
                flightCustomer.setGesamtmeilen(strecke);
                flightCustomer.setJahresmeilen(strecke);
                flightCustomerController.updateFlightCustomer(flightCustomer);

            } else if (buchungswahl == 2) {
                System.out.println("Flug nicht gebucht!!");
            } else {
                System.out.println("Buchung abgebrochen");
            }

            // "Kundenzähler" erhöhen um ID zu Inkrementieren
            i++;
        }

    }

    public static Date createDate(String eingabe){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date datum = null;
        try {
            datum = dateFormat.parse(eingabe);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return datum;
    }
}
