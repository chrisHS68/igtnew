package de.hsma.jens.controllers;


import de.hsma.jens.models.Airport;
import de.hsma.jens.models.Flight;
import de.hsma.jens.models.Flightsegment;
import de.hsma.jens.tools.Config;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.transaction.*;
import java.io.IOException;
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
    public void createAirport(List<Flightsegment> flightsegments){
        try {
            logger.info("Create Flightsegment TA begins");
            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            long queryStart = System.currentTimeMillis();

            for (Flightsegment f : flightsegments) {
                em.persist(f);
            }

            long queryEnd = System.currentTimeMillis();

            em.flush();
            em.close();
            tm.commit();


            logger.info("Create Flightsegment TA ends");
            long queryTime = queryEnd - queryStart;

            logger.info(flightsegments.size() + " Flightsegments persisted in DB in " + queryTime + " ms.");

            String writeToFile = new String(Config.PERSISTENCE_UNIT_NAME + " CREATE: " + flightsegments.size() + " " + queryTime + "\n");

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

    public Flightsegment getFlightsegment(int flightsegmentID){

        Flightsegment f = null;


        try {
            EntityManager em = emf.createEntityManager();
            tm.begin();


            f = em.find(Flightsegment.class, flightsegmentID);
            logger.info("Found Flightsegment: " + f.toString());


            em.flush();
            em.close();
            tm.commit();
            logger.info("TA ends");


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


        return f;
    };

    public List<Flightsegment> getAllFlightsegments(){

        List<Flightsegment> flightsegments = new ArrayList<Flightsegment>();


        try {
            EntityManager em = emf.createEntityManager();

            String queryString = new String("SELECT c FROM Flightsegment c");

            logger.info("Get all Flightsegment TA begins");
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();


            Query q = em.createQuery(queryString);

            long queryStart = System.currentTimeMillis();

            flightsegments = q.getResultList();

            long queryEnd = System.currentTimeMillis();


            em.flush();
            em.close();
            tm.commit();

            logger.info("Get all Flightsegment TA ends");

            long queryTime = queryEnd - queryStart;

            logger.info("Found " + flightsegments.size() + " Flightsegments in " + queryTime + " ms.");

            String writeToFile = new String(Config.PERSISTENCE_UNIT_NAME + " READ  : " + flightsegments.size() + " " + queryTime + "\n");
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


        return flightsegments;
    };

    public void updateFlightsegment(Flightsegment fs){
        try {
            logger.info("Update Flightsegment TA begins");
            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();


            long queryStart = System.currentTimeMillis();

            Flightsegment flightsegmentToUpdate = em.find(Flightsegment.class, fs.getId());
            logger.info("Found Flightsegment: " + flightsegmentToUpdate.toString());
            logger.info("Updating Flightsegment...");
            flightsegmentToUpdate = fs;

            em.merge(flightsegmentToUpdate);

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
    };
    public void updateFlightsegment(List<Flightsegment> flightsegments){
        try {
            EntityManager em = emf.createEntityManager();
            logger.info("Update Flightsegments TA begins");
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            long queryStart = System.currentTimeMillis();

            for (Flightsegment f : flightsegments) {

                em.merge(f);

            }

            long queryEnd = System.currentTimeMillis();


            em.flush();
            em.close();
            tm.commit();
            logger.info("Update Flightsegments TA ends");

            long queryTime = queryEnd - queryStart;

            logger.info("Updates of " + flightsegments.size() + " Flightsegments successfully persisted in " + queryTime + " ms.");

            String writeToFile = new String(Config.PERSISTENCE_UNIT_NAME + " UPDATE: " + flightsegments.size() + " " + queryTime + "\n");
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

    public void deleteFlightsegment(int flightsegmentID){
        try {
            logger.info("Delete Flightsegment TA begins");
            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();


            long queryStart = System.currentTimeMillis();

            Flightsegment flightsegment = em.find(Flightsegment.class, flightsegmentID);
            logger.info("Found Flightsegment: " + flightsegment.toString());
            logger.info("Deleting Flightsegment...");


            em.remove(flightsegment);

            long queryEnd = System.currentTimeMillis();


            em.flush();
            em.close();
            tm.commit();
            logger.info("Delete Flightsegment TA ends");

            long queryTime = queryEnd - queryStart;

            logger.info("Flightsegment successfully deleted in " + queryTime + " ms.");


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
    public void deleteAllFlightsegment(){
        List<Integer> flightsegmentIDs;

        try {


            flightsegmentIDs = getAllFlightsegmentIDs();


            logger.info("Delete all Flightsegments TA begins");


            long queryStart = System.currentTimeMillis();

            for (Integer id : flightsegmentIDs) {

                deleteFlightsegment(id);
            }


            long queryEnd = System.currentTimeMillis();


            logger.info("Delete all Flightsegments TA ends");
            long queryTime = queryEnd - queryStart;

            logger.info(flightsegmentIDs.size() + " Flightsegment successfully deleted in " + queryTime + " ms.");

            String writeToFile = new String(Config.PERSISTENCE_UNIT_NAME + " DELETE: " + flightsegmentIDs.size() + " " + queryTime + "\n");
            Files.write(Paths.get(Config.LOG_STORAGE_LOCATION), writeToFile.getBytes(), CREATE, APPEND);


        } catch (IOException e) {
            e.printStackTrace();
        }
    };

    public List<Integer> getAllFlightsegmentIDs(){

        List<Flightsegment> flightsegments = new ArrayList<Flightsegment>();
        List<Integer> returnList = new ArrayList<Integer>();

        try {
            EntityManager em = emf.createEntityManager();

            //String queryString = new String("SELECT c.C_ID FROM Customer c");
            /*
            some datastores (that uses indexes like Redis, Infinispan, Cassandra) cannot operate on single return values,
            they always need to return the entire class
             */

            String queryString = new String("SELECT c FROM Flightsegment c");
            Query q = em.createQuery(queryString);


            logger.info("Get all FlightsegmentIDs TA begins");
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            long queryStart = System.currentTimeMillis();

            flightsegments = q.getResultList();

            for (Flightsegment fs : flightsegments) {
                returnList.add(fs.getId());
            }


            long queryEnd = System.currentTimeMillis();


            em.flush();
            em.close();
            tm.commit();
            logger.info("Get all FlightsegmentIDs TA ends");

            long queryTime = queryEnd - queryStart;

            logger.info("Found " + flightsegments.size() + " Flightsegment IDs in " + queryTime + " ms.");


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

    };
}
