package de.hsma.jens.controllers;


import de.hsma.jens.models.FlightCustomer;
import de.hsma.jens.models.Flightsegment;
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

public class FlightsegmentController {
    private static Logger logger = Logger.getRootLogger();

    //accessing JBoss's Transaction can be done differently but this one works nicely
    TransactionManager tm = com.arjuna.ats.jta.TransactionManager.transactionManager();
    //build the EntityManagerFactory as you would build in in Hibernate ORM
    EntityManagerFactory emf = Persistence.createEntityManagerFactory(Config.PERSISTENCE_UNIT_NAME);

    //Inteface Methods
    public void createFlightSegment(List<Flightsegment> fsList){


        try {
            logger.info("Create Flightsegment TA begins");
            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            long queryStart = System.currentTimeMillis();

            for (Flightsegment fs : fsList) {
                em.persist(fs);
            }

            long queryEnd = System.currentTimeMillis();

            em.flush();
            em.close();
            tm.commit();


            logger.info("Create Flightsegment TA ends");
            long queryTime = queryEnd - queryStart;

            logger.info(fsList.size() + " Flightsegments persisted in DB in " + queryTime + " ms.");

            String writeToFile = new String(Config.PERSISTENCE_UNIT_NAME + " CREATE: " + fsList.size() + " " + queryTime + "\n");

            Files.write(Paths.get(Config.LOG_STORAGE_LOCATION), writeToFile.getBytes(), CREATE, APPEND);


        } catch (
                NotSupportedException e) {
            e.printStackTrace();
        } catch (
                SystemException e) {
            e.printStackTrace();
        } catch (
                HeuristicMixedException e) {
            e.printStackTrace();
        } catch (
                HeuristicRollbackException e) {
            e.printStackTrace();
        } catch (RollbackException e) {
            e.printStackTrace();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    };


    //Inteface Methods
    public List<Flightsegment> getAllFlightsegments() {

        List<Flightsegment> fs = new ArrayList<Flightsegment>();
        try {
            EntityManager em = emf.createEntityManager();

            String queryString = new String("SELECT f FROM Flightsegment f");

            System.out.println("Get all Flightsegments TA begins");
            //logger.info("Get all Flightsegments TA begins");
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            Query q = em.createQuery(queryString);

            long queryStart = System.currentTimeMillis();

            fs = q.getResultList();

            long queryEnd = System.currentTimeMillis();


            tm.commit();
            long queryTime = queryEnd - queryStart;

            //logger.info("Get all Flightsegments TA ends");
            System.out.println("Get all Flightsegments TA ends");
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


        return fs;
    }


    public void updateFlightsegment(Flightsegment fs) {

        try {
            logger.info("Update Flightsegment TA begins");
            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();


            long queryStart = System.currentTimeMillis();

            Flightsegment FlightsegmentToUpdate = em.find(Flightsegment.class, fs.getId());
            logger.info("Found Flightsegment: " + FlightsegmentToUpdate.toString());
            logger.info("Updating Flightsegment...");
            FlightsegmentToUpdate = fs;

            em.merge(FlightsegmentToUpdate);

            long queryEnd = System.currentTimeMillis();


            em.flush();
            em.close();
            tm.commit();
            logger.info("Update Flightsegment TA ends");

            long queryTime = queryEnd - queryStart;

            logger.info("Flightsegment successfully persisted in " + queryTime + " ms.");

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

    }

    public void updateFlightsegment(List<Flightsegment> flightsegmentList) {

        try {
            EntityManager em = emf.createEntityManager();
            logger.info("Update Flightsegment TA begins");
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            long queryStart = System.currentTimeMillis();

            for (Flightsegment fs : flightsegmentList) {

                em.merge(fs);

            }

            long queryEnd = System.currentTimeMillis();


            em.flush();
            em.close();
            tm.commit();
            logger.info("Update Flightsegment TA ends");

            long queryTime = queryEnd - queryStart;

            logger.info("Updates of " + flightsegmentList.size() + " Flightsegment successfully persisted in " + queryTime + " ms.");

            String writeToFile = new String(Config.PERSISTENCE_UNIT_NAME + " UPDATE: " + flightsegmentList.size() + " " + queryTime + "\n");
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


    public void deleteFlightsegment(int FlightsegmentID) {

        try {
            //logger.info("Delete Flightsegment TA begins");
            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();


            // long queryStart = System.currentTimeMillis();

            Flightsegment flightsegment = em.find(Flightsegment.class, FlightsegmentID);
            // logger.info("Found Flightsegment: " + cust.toString());
            // logger.info("Deleting Flightsegment...");


            em.remove(flightsegment);

            //  long queryEnd = System.currentTimeMillis();


            em.flush();
            em.close();
            tm.commit();
            //logger.info("Delete Flightsegment TA ends");

            //  long queryTime = queryEnd - queryStart;

            // logger.info("Flightsegment successfully deleted in " + queryTime + " ms.");


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
    }


    public List<Flightsegment> getALLFlightsegments() {
        List<Flightsegment> flightsegment = new ArrayList<Flightsegment>();


        try {
            EntityManager em = emf.createEntityManager();

            String queryString = new String("SELECT f FROM Flightsegment f");

            logger.info("Get all Flightsegments TA begins");
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();


            Query q = em.createQuery(queryString);

            long queryStart = System.currentTimeMillis();

            flightsegment = q.getResultList();

            long queryEnd = System.currentTimeMillis();


            em.flush();
            em.close();
            tm.commit();

            logger.info("Get all Flightsegments TA ends");

            long queryTime = queryEnd - queryStart;

            logger.info("Found " + flightsegment.size() + " Flightsegments in " + queryTime + " ms.");

            String writeToFile = new String(Config.PERSISTENCE_UNIT_NAME + " READ  : " + flightsegment.size() + " " + queryTime + "\n");
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


        return flightsegment;

    }

    public void deleteAllFlightsegment() {

        List<Integer> flightsegment;

        try {


            flightsegment = getAllFlightsegmentIDs();


            logger.info("Delete all Flightsegments TA begins");


            long queryStart = System.currentTimeMillis();

            for (Integer id : flightsegment) {

                deleteFlightsegment(id);
            }


            long queryEnd = System.currentTimeMillis();


            logger.info("Delete all Flightsegments TA ends");
            long queryTime = queryEnd - queryStart;

            logger.info(flightsegment.size() + " Flightsegments successfully deleted in " + queryTime + " ms.");

            String writeToFile = new String(Config.PERSISTENCE_UNIT_NAME + " DELETE: " + flightsegment.size() + " " + queryTime + "\n");
            Files.write(Paths.get(Config.LOG_STORAGE_LOCATION), writeToFile.getBytes(), CREATE, APPEND);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public List<Integer> getAllFlightsegmentIDs() {

        List<Flightsegment> fsIDs = new ArrayList<Flightsegment>();
        List<Integer> returnList = new ArrayList<Integer>();

        try {
            EntityManager em = emf.createEntityManager();

            //String queryString = new String("SELECT f.F_ID FROM Flightsegment f");
            /*
            some datastores (that uses indexes like Redis, Infinispan, Cassandra) cannot operate on single return values,
            they always need to return the entire class
             */

            String queryString = new String("SELECT f FROM Flightsegment f");
            Query q = em.createQuery(queryString);


            logger.info("Get all FlightsegmentIDs TA begins");
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            long queryStart = System.currentTimeMillis();

            fsIDs = q.getResultList();

            for (Flightsegment fs : fsIDs) {
                returnList.add(fs.getId());
            }


            long queryEnd = System.currentTimeMillis();


            em.flush();
            em.close();
            tm.commit();
            logger.info("Get all FlightsegmentIDs TA ends");

            long queryTime = queryEnd - queryStart;

            logger.info("Found " + fsIDs.size() + " FlightsegmentIDs in " + queryTime + " ms.");


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

    public Flightsegment getFlightsegment(int flightsegmentID) {


        Flightsegment flightsegment = null;


        try {
            EntityManager em = emf.createEntityManager();
            tm.begin();


            flightsegment = em.find(Flightsegment.class, flightsegmentID);
            // logger.info("Found Flightsegment: " + flightsegment.toString());


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


        return flightsegment;


    }

    public Integer getAllFlightsegmentAsList() {


        ObjectOutputStream oos = null;
        FileOutputStream fos = null;
        List<Integer> numberOfFlightsegment = null;


        try {


            fos = new FileOutputStream(Config.PERSIST_STORAGE_OUTPUT_LOCATION);
            oos = new ObjectOutputStream(fos);

            EntityManager em = emf.createEntityManager();


            String queryString = new String("FROM Flightsegment");


            javax.persistence.Query query = em.createQuery(queryString);

            Session session = em.unwrap(Session.class);

            /*
            Hibernate OGM: not implemented yet
             */

            ScrollableResults results = session
                    .createQuery("FROM Flightsegment")
                    .setCacheMode(CacheMode.IGNORE)
                    .scroll(ScrollMode.FORWARD_ONLY);


            numberOfFlightsegment = getAllFlightsegmentIDs();

            logger.info("Get all Flightsegment TA begins");
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();


            long queryStart = System.currentTimeMillis();


            while (results.next()) {
                Flightsegment fs = (Flightsegment) results.get(0);
                fs.toString();
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

            logger.info("Get all Flightsegment TA ends");

            long queryTime = queryEnd - queryStart;

            logger.info("Found " + numberOfFlightsegment.size() + " Flightsegment in " + queryTime + " ms.");

            String writeToFile = new String(Config.PERSISTENCE_UNIT_NAME + " READ  : " + numberOfFlightsegment.size() + " " + queryTime + "\n");
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

        return numberOfFlightsegment.size();


    }
}