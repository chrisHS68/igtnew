
import de.hsma.jens.models.CustomerAddress;
import de.hsma.jens.models.FlightCustomer;
import de.hsma.jens.models.PhoneType;
import de.hsma.jens.models.Status;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        //Abflughafen auswählen
        System.out.println("Abflughafen wählen");

        Scanner scanner = new Scanner(System. in);
        Integer abflugEingabe = Integer.parseInt(scanner.nextLine());
        System.out.println("Gewählter Abflughafen: "+ abflugEingabe);

        //Zielflughafen auswählen
        System.out.println("Zielflughafen wählen");
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
        System.out.println("Kein Flug verfügbar");

        //Teilfluganbieten

        //Strecke Berechnen
        int strecke = 800;
        System.out.println("Die Strecke beträgt: "+strecke+"km");

        //Abflug und Ankunftszeit anzeigen
        int abflugzeit = 1;
        int ankunftzeit = 1;
        System.out.println("Ablugzeit: "+abflugzeit);
        System.out.println("Ankuftzeit: "+ankunftzeit);

        //Sitzplatz Art wählen
        System.out.println("Flugklasse wählen:");
        System.out.println("(1)Erste Klasse");
        System.out.println("(2)Economy");
        int auswahl = Integer.parseInt(scanner.nextLine());

        //Kundendaten eingeben
        FlightCustomer flightCustomer = new FlightCustomer();
        flightCustomer.setId(1);

        CustomerAddress customerAddress = new CustomerAddress();
        customerAddress.setId(1);
        System.out.println("Straße eingeben: ");
        customerAddress.setStrasse(scanner.nextLine());
        System.out.println("Hausnummer eingeben: ");
        customerAddress.setHausnummer(scanner.nextLine());
        System.out.println("PLZ eingeben: ");
        customerAddress.setPlz(scanner.nextLine());
        System.out.println("Ort eigeben: ");
        customerAddress.setOrt(scanner.nextLine());

        PhoneType phone = new PhoneType();
        phone.setId(1);
        System.out.println("Telefonnummer eingeben:");
        int phonenumber = Integer.parseInt(scanner.nextLine());
        phone.setNumber(new Long(phonenumber));

        flightCustomer.setCustomerAddress(customerAddress);

        //Kundenstatus anzeigen
        Status kundestatus = Status.NONE;
        System.out.println("Aktueller Kundenstatus: " + kundestatus);

        //Preisermittlung starten
        Integer preis = 120;
        System.out.println("Flugpreis" + preis + "€");

        //Flug Buchen
        System.out.println("Flug buchen? ");
        System.out.println("1: JA ");
        System.out.println("2: NEIN");
        int buchungswahl = Integer.parseInt(scanner.nextLine());
        if(buchungswahl == 1){
            System.out.println("Flug gebucht");
        }else if (buchungswahl == 2){
            System.out.println("Flug nicht gebucht!!");
        }else {
            System.out.println("Buchung abgebrochen");
        }


    }

}
