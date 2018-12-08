package de.hsma.jens.controllers;


import de.hsma.jens.models.Flight;
import de.hsma.jens.models.Flugzeug;
import de.hsma.jens.tools.Config;
import org.apache.log4j.Logger;
import org.hibernate.CacheMode;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.transaction.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

public class FlightController {
    private static Logger logger = Logger.getRootLogger();

    //accessing JBoss's Transaction can be done differently but this one works nicely
    TransactionManager tm = com.arjuna.ats.jta.TransactionManager.transactionManager();
    //build the EntityManagerFactory as you would build in in Hibernate ORM
    EntityManagerFactory emf = Persistence.createEntityManagerFactory(Config.PERSISTENCE_UNIT_NAME);

    //Inteface Methods
    public void createFlight(List<Flight> fList){

        try {
            logger.info("Create flight TA begins");
            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            long queryStart = System.currentTimeMillis();

            for (Flight fl : fList) {
                em.persist(fl.getFlugzeug());
                em.persist(fl.getSegments().get(0).getAbflughafen());
                em.persist(fl.getSegments().get(0).getZielflughafen());
                em.persist(fl.getSegments().get(0));
                em.persist(fl);
            }

            long queryEnd = System.currentTimeMillis();

            em.flush();
            em.close();
            tm.commit();


            logger.info("Create flight TA ends");
            long queryTime = queryEnd - queryStart;

            logger.info(fList.size() + " flight persisted in DB in " + queryTime + " ms.");

            String writeToFile = new String(Config.PERSISTENCE_UNIT_NAME + " CREATE: " + fList.size() + " " + queryTime + "\n");

            Files.write(Paths.get(Config.LOG_STORAGE_LOCATION), writeToFile.getBytes(), CREATE, APPEND);


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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public List<Flight> getAllFlight() {

        List<Flight> fl = new ArrayList<Flight>();
        try {
        EntityManager em = emf.createEntityManager();

        String queryString = new String("SELECT f FROM Flight f");

        System.out.println("Get all flights TA begins");
        //logger.info("Get all flights TA begins");
        tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
        tm.begin();

        Query q = em.createQuery(queryString);

        long queryStart = System.currentTimeMillis();

        fl = q.getResultList();

        long queryEnd = System.currentTimeMillis();


        tm.commit();
        long queryTime = queryEnd - queryStart;

        //logger.info("Get all customer TA ends");
        System.out.println("Get all flights TA ends");
        System.out.println("Time:" + queryTime);
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
        }


        return fl;
        }


    public Flight getFlight(int FlightID){
        Flight fl = null;


        try {
            EntityManager em = emf.createEntityManager();
            tm.begin();


            fl = em.find(Flight.class, FlightID);
            // logger.info("Found flight: " + flightfl.toString());


            em.flush();
            em.close();
            tm.commit();
            // logger.info("TA ends");


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
        }


        return fl;
    };


    public void updateFlight(Flight fl){

        try {
            logger.info("Update flight TA begins");
            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();


            long queryStart = System.currentTimeMillis();

            Flight flightToUpdate = em.find(Flight.class, fl.getId());
            logger.info("Found flight: " + flightToUpdate.toString());
            logger.info("Updating flight...");
            flightToUpdate = fl;

            em.merge(flightToUpdate);

            long queryEnd = System.currentTimeMillis();


            em.flush();
            em.close();
            tm.commit();
            logger.info("Update flight TA ends");

            long queryTime = queryEnd - queryStart;

            logger.info("Flight successfully persisted in " + queryTime + " ms.");

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
        }

    };
    public void updateFlight(List<Flight> flightList){

        try {
            EntityManager em = emf.createEntityManager();
            logger.info("Update flights TA begins");
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            long queryStart = System.currentTimeMillis();

            for (Flight fl : flightList) {

                em.merge(fl);

            }

            long queryEnd = System.currentTimeMillis();


            em.flush();
            em.close();
            tm.commit();
            logger.info("Update flights TA ends");

            long queryTime = queryEnd - queryStart;

            logger.info("Updates of " + flightList.size() + " flights successfully persisted in " + queryTime + " ms.");

            String writeToFile = new String(Config.PERSISTENCE_UNIT_NAME + " UPDATE: " + flightList.size() + " " + queryTime + "\n");
            Files.write(Paths.get(Config.LOG_STORAGE_LOCATION), writeToFile.getBytes(), CREATE, APPEND);


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
        } catch (IOException e) {
            e.printStackTrace();
        }

    };

    public void deleteFlight(int FlightID){

        try {
            //logger.info("Delete flight TA begins");
            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();


            // long queryStart = System.currentTimeMillis();

            Flight fl = em.find(Flight.class, FlightID);
            // logger.info("Found flight: " + fl.toString());
            // logger.info("Deleting flight...");


            em.remove(fl);

            //  long queryEnd = System.currentTimeMillis();


            em.flush();
            em.close();
            tm.commit();
            //logger.info("Delete flight TA ends");

            //  long queryTime = queryEnd - queryStart;

            // logger.info("Flight successfully deleted in " + queryTime + " ms.");


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
        }

    };

    public void deleteAllFlight(){
        List<Integer> fl;

        try {


            fl = getAllFlightIDs();


            logger.info("Delete all flights TA begins");


            long queryStart = System.currentTimeMillis();

            for (Integer id : fl) {

                deleteFlight(id);
            }


            long queryEnd = System.currentTimeMillis();


            logger.info("Delete all flights TA ends");
            long queryTime = queryEnd - queryStart;

            logger.info(fl.size() + " flights successfully deleted in " + queryTime + " ms.");

            String writeToFile = new String(Config.PERSISTENCE_UNIT_NAME + " DELETE: " + fl.size() + " " + queryTime + "\n");
            Files.write(Paths.get(Config.LOG_STORAGE_LOCATION), writeToFile.getBytes(), CREATE, APPEND);


        } catch (IOException e) {
            e.printStackTrace();
        }

    };

    public List<Integer> getAllFlightIDs() {

        List<Flight> flIDs = new ArrayList<Flight>();
        List<Integer> returnList = new ArrayList<Integer>();

        try {
            EntityManager em = emf.createEntityManager();

            //String queryString = new String("SELECT f.F_ID FROM Flight f");
            /*
            some datastores (that uses indexes like Redis, Infinispan, Cassandra) cannot operate on single return values,
            they always need to return the entire class
             */

            String queryString = new String("SELECT f FROM Flight f");
            Query q = em.createQuery(queryString);


            logger.info("Get all flightIDs TA begins");
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            long queryStart = System.currentTimeMillis();

            flIDs = q.getResultList();

            for (Flight fl : flIDs) {
                returnList.add(fl.getId());
            }


            long queryEnd = System.currentTimeMillis();


            em.flush();
            em.close();
            tm.commit();
            logger.info("Get all flightIDs TA ends");

            long queryTime = queryEnd - queryStart;

            logger.info("Found " + flIDs.size() + " flight IDs in " + queryTime + " ms.");


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
        }


        return returnList;

    }

    public Integer getAllFlightsAsList() {


        ObjectOutputStream oos = null;
        FileOutputStream fos = null;
        List<Integer> numberOfFlights = null;


        try {


            fos = new FileOutputStream(Config.PERSIST_STORAGE_OUTPUT_LOCATION);
            oos = new ObjectOutputStream(fos);

            EntityManager em = emf.createEntityManager();


            String queryString = new String("FROM Flight");


            javax.persistence.Query query = em.createQuery(queryString);

            Session session = em.unwrap(Session.class);

            /*
            Hibernate OGM: not implemented yet
             */

            ScrollableResults results = session
                    .createQuery("FROM Flight")
                    .setCacheMode(CacheMode.IGNORE)
                    .scroll(ScrollMode.FORWARD_ONLY);


            numberOfFlights = getAllFlightIDs();

            logger.info("Get all flights TA begins");
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();


            long queryStart = System.currentTimeMillis();


            while (results.next()) {
                Flight fl = (Flight) results.get(0);
                fl.toString();
            }
            results.close();


            long queryEnd = System.currentTimeMillis();


            oos.writeObject(null);
            fos.flush();
            fos.close();
            oos.flush();
            oos.close();

            em.flush();
            em.close();
            tm.commit();

            logger.info("Get all flights TA ends");

            long queryTime = queryEnd - queryStart;

            logger.info("Found " + numberOfFlights.size() + " flights in " + queryTime + " ms.");

            String writeToFile = new String(Config.PERSISTENCE_UNIT_NAME + " READ  : " + numberOfFlights.size() + " " + queryTime + "\n");
            Files.write(Paths.get(Config.LOG_STORAGE_LOCATION), writeToFile.getBytes(), CREATE, APPEND);


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
        } catch (IOException e) {
            e.printStackTrace();
        }

        return numberOfFlights.size();


    }



};
