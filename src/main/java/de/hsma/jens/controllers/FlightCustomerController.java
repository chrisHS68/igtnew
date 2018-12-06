package de.hsma.jens.controllers;

import de.hsma.jens.models.*;

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

import java.io.IOException;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

public class FlightCustomerController {
    private static Logger logger = Logger.getRootLogger();

    //accessing JBoss's Transaction can be done differently but this one works nicely
    TransactionManager tm = com.arjuna.ats.jta.TransactionManager.transactionManager();
    //build the EntityManagerFactory as you would build in in Hibernate ORM
    EntityManagerFactory emf = Persistence.createEntityManagerFactory(Config.PERSISTENCE_UNIT_NAME);

    public void createFlightCustomers(List<FlightCustomer> cList) {
        try {
            logger.info("Create customers TA begins");
            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            long queryStart = System.currentTimeMillis();

            for (FlightCustomer fc : cList) {
                em.persist(fc);
            }

            long queryEnd = System.currentTimeMillis();

            em.flush();
            em.close();
            tm.commit();


            logger.info("Create customers TA ends");
            long queryTime = queryEnd - queryStart;

            logger.info(cList.size() + " customers persisted in DB in " + queryTime + " ms.");

            String writeToFile = new String(Config.PERSISTENCE_UNIT_NAME + " CREATE: " + cList.size() + " " + queryTime + "\n");

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

    //Inteface Methods

  /*  public void createFlightCustomer(FlightCustomer fc){
        try{
            logger.info("Create customer TA begins");
            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();
            long queryStart = System.currentTimeMillis();
            em.persist(fc);
            long queryEnd = System.currentTimeMillis();
            em.flush();
            em.close();
            tm.commit();
            logger.info("Create customers TA ends");
            long queryTime = queryEnd - queryStart;
            logger.info(1 + " customers persisted in DB in " + queryTime + " ms.");
            String writeToFile = new String(Config.PERSISTENCE_UNIT_NAME + " CREATE: " + 1 + " " + queryTime + "\n");
            Files.write(Paths.get(Config.LOG_STORAGE_LOCATION), writeToFile.getBytes(), CREATE, APPEND);
            System.out.println("Time for Statement: " + queryTime);
        }catch (Exception e){
            e.printStackTrace();
        }
    };*/;


    public List<FlightCustomer> getAllFlightCustomers() {

        List<FlightCustomer> fc = new ArrayList<FlightCustomer>();
        try {
            EntityManager em = emf.createEntityManager();

            String queryString = new String("SELECT c FROM FlightCustomer c");

            System.out.println("Get all customer TA begins");
            //logger.info("Get all customer TA begins");
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            Query q = em.createQuery(queryString);

            long queryStart = System.currentTimeMillis();

            fc = q.getResultList();

            long queryEnd = System.currentTimeMillis();


            tm.commit();
            long queryTime = queryEnd - queryStart;

            //logger.info("Get all customer TA ends");
            System.out.println("Get all customer TA ends");
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


        return fc;
    }


    public void FlightCustomer(List<FlightCustomer> fc) {

    }


    public void updateFlightCustomer(FlightCustomer fc) {

        try {
            logger.info("Update flightcustomer TA begins");
            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();


            long queryStart = System.currentTimeMillis();

            FlightCustomer customerToUpdate = em.find(FlightCustomer.class, fc.getId());
            logger.info("Found flightcustomer: " + customerToUpdate.toString());
            logger.info("Updating flightcustomer...");
            customerToUpdate = fc;

            em.merge(customerToUpdate);

            long queryEnd = System.currentTimeMillis();


            em.flush();
            em.close();
            tm.commit();
            logger.info("Update flightcustomer TA ends");

            long queryTime = queryEnd - queryStart;

            logger.info("FlightCustomer successfully persisted in " + queryTime + " ms.");

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

    public void updateFlightCustomer(List<FlightCustomer> flightCustomerList) {

        try {
            EntityManager em = emf.createEntityManager();
            logger.info("Update flightcustomers TA begins");
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            long queryStart = System.currentTimeMillis();

            for (FlightCustomer fc : flightCustomerList) {

                em.merge(fc);

            }

            long queryEnd = System.currentTimeMillis();


            em.flush();
            em.close();
            tm.commit();
            logger.info("Update flightcustomers TA ends");

            long queryTime = queryEnd - queryStart;

            logger.info("Updates of " + flightCustomerList.size() + " flightcustomers successfully persisted in " + queryTime + " ms.");

            String writeToFile = new String(Config.PERSISTENCE_UNIT_NAME + " UPDATE: " + flightCustomerList.size() + " " + queryTime + "\n");
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


    public void deleteFlightCustomer(int FlightCustomerID) {

        try {
            //logger.info("Delete flightcustomer TA begins");
            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();


            // long queryStart = System.currentTimeMillis();

            FlightCustomer cust = em.find(FlightCustomer.class, FlightCustomerID);
            // logger.info("Found flightcustomer: " + cust.toString());
            // logger.info("Deleting flightcustomer...");


            em.remove(cust);

            //  long queryEnd = System.currentTimeMillis();


            em.flush();
            em.close();
            tm.commit();
            //logger.info("Delete flightcustomer TA ends");

            //  long queryTime = queryEnd - queryStart;

            // logger.info("FlightCustomer successfully deleted in " + queryTime + " ms.");


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


    public List<FlightCustomer> getALLFlightCustomer() {
        List<FlightCustomer> cust = new ArrayList<FlightCustomer>();


        try {
            EntityManager em = emf.createEntityManager();

            String queryString = new String("SELECT c FROM FlightCustomer c");

            logger.info("Get all flightcustomer TA begins");
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();


            Query q = em.createQuery(queryString);

            long queryStart = System.currentTimeMillis();

            cust = q.getResultList();

            long queryEnd = System.currentTimeMillis();


            em.flush();
            em.close();
            tm.commit();

            logger.info("Get all flightcustomer TA ends");

            long queryTime = queryEnd - queryStart;

            logger.info("Found " + cust.size() + " flightcustomers in " + queryTime + " ms.");

            String writeToFile = new String(Config.PERSISTENCE_UNIT_NAME + " READ  : " + cust.size() + " " + queryTime + "\n");
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


        return cust;

    }

    public void deleteAllFlightCustomer() {

        List<Integer> cust;

        try {


            cust = getAllFlightCustomerIDs();


            logger.info("Delete all customers TA begins");


            long queryStart = System.currentTimeMillis();

            for (Integer id : cust) {

                deleteFlightCustomer(id);
            }


            long queryEnd = System.currentTimeMillis();


            logger.info("Delete all flightcustomers TA ends");
            long queryTime = queryEnd - queryStart;

            logger.info(cust.size() + " flightcustomers successfully deleted in " + queryTime + " ms.");

            String writeToFile = new String(Config.PERSISTENCE_UNIT_NAME + " DELETE: " + cust.size() + " " + queryTime + "\n");
            Files.write(Paths.get(Config.LOG_STORAGE_LOCATION), writeToFile.getBytes(), CREATE, APPEND);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public List<Integer> getAllFlightCustomerIDs() {

        List<FlightCustomer> fcIDs = new ArrayList<FlightCustomer>();
        List<Integer> returnList = new ArrayList<Integer>();

        try {
            EntityManager em = emf.createEntityManager();

            //String queryString = new String("SELECT c.C_ID FROM Customer c");
            /*
            some datastores (that uses indexes like Redis, Infinispan, Cassandra) cannot operate on single return values,
            they always need to return the entire class
             */

            String queryString = new String("SELECT c FROM Customer c");
            Query q = em.createQuery(queryString);


            logger.info("Get all flightcustomerIDs TA begins");
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            long queryStart = System.currentTimeMillis();

            fcIDs = q.getResultList();

            for (FlightCustomer fc : fcIDs) {
                returnList.add(fc.getId());
            }


            long queryEnd = System.currentTimeMillis();


            em.flush();
            em.close();
            tm.commit();
            logger.info("Get all flightcustomerIDs TA ends");

            long queryTime = queryEnd - queryStart;

            logger.info("Found " + fcIDs.size() + " flightcustomer IDs in " + queryTime + " ms.");


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

    public FlightCustomer getFlightCustomer(int flightcustomerID) {


        FlightCustomer cust = null;


        try {
            EntityManager em = emf.createEntityManager();
            tm.begin();


            cust = em.find(FlightCustomer.class, flightcustomerID);
            // logger.info("Found customer: " + flightcust.toString());


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


        return cust;


    }

    public Integer getAllFlightCustomerAsList() {


        ObjectOutputStream oos = null;
        FileOutputStream fos = null;
        List<Integer> numberOfFlightCustomers = null;


        try {


            fos = new FileOutputStream(Config.PERSIST_STORAGE_OUTPUT_LOCATION);
            oos = new ObjectOutputStream(fos);

            EntityManager em = emf.createEntityManager();


            String queryString = new String("FROM Customer");


            javax.persistence.Query query = em.createQuery(queryString);

            Session session = em.unwrap(Session.class);

            /*
            Hibernate OGM: not implemented yet
             */

            ScrollableResults results = session
                    .createQuery("FROM FlightCustomer")
                    .setCacheMode(CacheMode.IGNORE)
                    .scroll(ScrollMode.FORWARD_ONLY);


            numberOfFlightCustomers = getAllFlightCustomerIDs();

            logger.info("Get all flightcustomer TA begins");
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();


            long queryStart = System.currentTimeMillis();


            while (results.next()) {
                FlightCustomer fc = (FlightCustomer) results.get(0);
                fc.toString();
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

            logger.info("Get all flightcustomer TA ends");

            long queryTime = queryEnd - queryStart;

            logger.info("Found " + numberOfFlightCustomers.size() + " flightcustomers in " + queryTime + " ms.");

            String writeToFile = new String(Config.PERSISTENCE_UNIT_NAME + " READ  : " + numberOfFlightCustomers.size() + " " + queryTime + "\n");
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

        return numberOfFlightCustomers.size();


    }


}