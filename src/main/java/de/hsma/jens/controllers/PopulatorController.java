package de.hsma.jens.controllers;

import de.hsma.jens.models.FlightCustomer;
import de.hsma.jens.tools.Config;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static de.hsma.jens.tools.FlightCustomerPopulator.populateCustomerAsList;
import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

public class PopulatorController {
    private static Logger logger = Logger.getRootLogger();
    //accessing JBoss's Transaction can be done differently but this one works nicely
    TransactionManager tm = com.arjuna.ats.jta.TransactionManager.transactionManager();
    //build the EntityManagerFactory as you would build in in Hibernate ORM
    EntityManagerFactory emf = Persistence.createEntityManagerFactory(Config.PERSISTENCE_UNIT_NAME);

    public void createFlightCustomer(FlightCustomer flightCustomer) {
        try {
            logger.info("Create customer TA begins");
            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            long queryStart = System.currentTimeMillis();

            em.persist(populateCustomerAsList(100));

           // em.persist(flightCustomer.getFlightCustomerAsList());
           // em.persist(flightCustomer);


            long queryEnd = System.currentTimeMillis();

            em.flush();
            em.close();
            tm.commit();


            logger.info("Create customers TA ends");
            long queryTime = queryEnd - queryStart;

            logger.info("Customers persisted in DB in " + queryTime + " ms.");

            System.out.println(queryTime);

            //String writeToFile = new String(Config.PERSISTENCE_UNIT_NAME + " CREATE: " + 1 + " " + queryTime + "\n");

            //Files.write(Paths.get(Config.LOG_STORAGE_LOCATION), writeToFile.getBytes(), CREATE, APPEND);


        } catch (NotSupportedException e) {
            e.printStackTrace();
        } catch (SystemException e) {
            e.printStackTrace();
        } catch (HeuristicMixedException e) {
            e.printStackTrace();
        } catch (HeuristicRollbackException e) {
            e.printStackTrace();
        } catch (RollbackException e) {
            e.printStackTrace();
       // } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
