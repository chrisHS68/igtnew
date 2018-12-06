package de.hsma.jens.controllers;

import de.hsma.jens.models.Airport;
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

public class AirportController {
    private static Logger logger = Logger.getRootLogger();

    //accessing JBoss's Transaction can be done differently but this one works nicely
    TransactionManager tm = com.arjuna.ats.jta.TransactionManager.transactionManager();
    //build the EntityManagerFactory as you would build in in Hibernate ORM
    EntityManagerFactory emf = Persistence.createEntityManagerFactory(Config.PERSISTENCE_UNIT_NAME);

    //Interface Methods
    public void createAirport(List<Airport>aList){
        try {
            logger.info("Create Airport TA begins");
            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            long queryStart = System.currentTimeMillis();

            for (Airport a : aList) {
                em.persist(a);
            }

            long queryEnd = System.currentTimeMillis();

            em.flush();
            em.close();
            tm.commit();


            logger.info("Create Airport TA ends");
            long queryTime = queryEnd - queryStart;

            logger.info(aList.size() + " Airports persisted in DB in " + queryTime + " ms.");

            String writeToFile = new String(Config.PERSISTENCE_UNIT_NAME + " CREATE: " + aList.size() + " " + queryTime + "\n");

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


    public Airport getAirport(int airportID){

        Airport a = null;


        try {
            EntityManager em = emf.createEntityManager();
            tm.begin();


            a = em.find(Airport.class, airportID);
            logger.info("Found customer: " + a.toString());


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


        return a;

    };

    public List<Airport> getAllAirports(){


        List<Airport> airport = new ArrayList<Airport>();


        try {
            EntityManager em = emf.createEntityManager();

            String queryString = new String("SELECT c FROM Airport c");

            logger.info("Get all Airports TA begins");
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();


            Query q = em.createQuery(queryString);

            long queryStart = System.currentTimeMillis();

            airport = q.getResultList();

            long queryEnd = System.currentTimeMillis();


            em.flush();
            em.close();
            tm.commit();

            logger.info("Get all Airports TA ends");

            long queryTime = queryEnd - queryStart;

            logger.info("Found " + airport.size() + " airports in " + queryTime + " ms.");

            String writeToFile = new String(Config.PERSISTENCE_UNIT_NAME + " READ  : " + airport.size() + " " + queryTime + "\n");
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


        return airport;

    };

    public void updateAirport(Airport ap){

        try {
            logger.info("Update Airport TA begins");
            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();


            long queryStart = System.currentTimeMillis();

            Airport airportToUpdate = em.find(Airport.class, ap.getId());
            logger.info("Found Airport: " + airportToUpdate.toString());
            logger.info("Updating Airport...");
            airportToUpdate = ap;

            em.merge(airportToUpdate);

            long queryEnd = System.currentTimeMillis();


            em.flush();
            em.close();
            tm.commit();
            logger.info("Update Airport TA ends");

            long queryTime = queryEnd - queryStart;

            logger.info("Airport successfully persisted in " + queryTime + " ms.");

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
    public void updateAirports(List<Airport> apList){

        try {
            EntityManager em = emf.createEntityManager();
            logger.info("Update Airports TA begins");
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            long queryStart = System.currentTimeMillis();

            for (Airport a : apList) {

                em.merge(a);

            }

            long queryEnd = System.currentTimeMillis();


            em.flush();
            em.close();
            tm.commit();
            logger.info("Update Airport TA ends");

            long queryTime = queryEnd - queryStart;

            logger.info("Updates of " + apList.size() + " Airports successfully persisted in " + queryTime + " ms.");

            String writeToFile = new String(Config.PERSISTENCE_UNIT_NAME + " UPDATE: " + apList.size() + " " + queryTime + "\n");
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

    public void deleteAirport(int airportID){

        try {
            logger.info("Delete Airport TA begins");
            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();


            long queryStart = System.currentTimeMillis();

            Airport airport = em.find(Airport.class, airportID);
            logger.info("Found Airport: " + airport.toString());
            logger.info("Deleting Airport...");


            em.remove(airport);

            long queryEnd = System.currentTimeMillis();


            em.flush();
            em.close();
            tm.commit();
            logger.info("Delete Airport TA ends");

            long queryTime = queryEnd - queryStart;

            logger.info("Airport successfully deleted in " + queryTime + " ms.");


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
    public void deleteAllAirport() {
        List<Integer> airportIDs;

        try {


            airportIDs = getAllAirportIDs();


            logger.info("Delete all customers TA begins");


            long queryStart = System.currentTimeMillis();

            for (Integer id : airportIDs) {

                deleteAirport(id);
            }


            long queryEnd = System.currentTimeMillis();


            logger.info("Delete all Airports TA ends");
            long queryTime = queryEnd - queryStart;

            logger.info(airportIDs.size() + " Airports successfully deleted in " + queryTime + " ms.");

            String writeToFile = new String(Config.PERSISTENCE_UNIT_NAME + " DELETE: " + airportIDs.size() + " " + queryTime + "\n");
            Files.write(Paths.get(Config.LOG_STORAGE_LOCATION), writeToFile.getBytes(), CREATE, APPEND);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        public List<Integer> getAllAirportIDs(){

            List<Airport> airportIDs = new ArrayList<Airport>();
            List<Integer> returnList = new ArrayList<Integer>();

            try {
                EntityManager em = emf.createEntityManager();

                //String queryString = new String("SELECT c.C_ID FROM Customer c");
            /*
            some datastores (that uses indexes like Redis, Infinispan, Cassandra) cannot operate on single return values,
            they always need to return the entire class
             */

                String queryString = new String("SELECT c FROM Airport c");
                Query q = em.createQuery(queryString);


                logger.info("Get all AirportIDs TA begins");
                tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
                tm.begin();

                long queryStart = System.currentTimeMillis();

                airportIDs = q.getResultList();

                for (Airport fc : airportIDs) {
                    returnList.add(fc.getId());
                }


                long queryEnd = System.currentTimeMillis();


                em.flush();
                em.close();
                tm.commit();
                logger.info("Get all AirportIDs TA ends");

                long queryTime = queryEnd - queryStart;

                logger.info("Found " + airportIDs.size() + " Airport IDs in " + queryTime + " ms.");


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
    };




