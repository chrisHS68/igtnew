
import de.hsma.jens.controllers.FlugzeugController;
import de.hsma.jens.models.Flugzeug;
import de.hsma.jens.models.Status;
import org.junit.After;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class FlugzeugControllerTest {
/**   @Test
    public void createFlugzeugControllerTest() {
        Flugzeug fl = new Flugzeug();

        fl.setId(1);
        fl.setPreis_economy(200);
        fl.setPreis_ersteklasse(300);
        fl.setSitzplaetze_economy(400);
        fl.setSitzplaetze_ersteklasse(100);
        fl.setTypbezeichnung("Condor");


        FlugzeugController flugzeugController = new FlugzeugController();
        List<Flugzeug> flugzeug = new ArrayList<>();
        flugzeug.add(fl);
        flugzeugController.createFlugzeug(flugzeug);
        flugzeugController
                .getALLFlugzeug()
                .forEach(flugzeug1 -> System.out.println(flugzeug1.toString()));
    }
/**/

}
