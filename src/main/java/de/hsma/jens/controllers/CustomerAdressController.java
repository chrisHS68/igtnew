package de.hsma.jens.controllers;

import de.hsma.jens.models.CustomerAddress;
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

public class CustomerAdressController {

    private static Logger logger = Logger.getRootLogger();
    //accessing JBoss's Transaction can be done differently but this one works nicely
    TransactionManager tm = com.arjuna.ats.jta.TransactionManager.transactionManager();
    //build the EntityManagerFactory as you would build in in Hibernate ORM
    EntityManagerFactory emf = Persistence.createEntityManagerFactory(Config.PERSISTENCE_UNIT_NAME);

    //Interface Methods
    public void createAdress(List<CustomerAddress> caList) {
        try {
            logger.info("Create addess TA begins");
            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            long queryStart = System.currentTimeMillis();

            for (CustomerAddress fc : caList) {
                em.persist(fc);
            }

            long queryEnd = System.currentTimeMillis();

            em.flush();
            em.close();
            tm.commit();


            logger.info("Create Address TA ends");
            long queryTime = queryEnd - queryStart;

            logger.info(caList.size() + " address persisted in DB in " + queryTime + " ms.");

            String writeToFile = new String(Config.PERSISTENCE_UNIT_NAME + " CREATE: " + caList.size() + " " + queryTime + "\n");

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

    ;

    public CustomerAddress getAdress(int addressID) {
        CustomerAddress ca = null;


        try {
            EntityManager em = emf.createEntityManager();
            tm.begin();

            ca = em.find(CustomerAddress.class, addressID);
            logger.info("Found customer: " + ca.toString());


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


        return ca;

    }

    ;

    public List<CustomerAddress> getAllAdress() {

        List<CustomerAddress> ca = new ArrayList<CustomerAddress>();
        try {
            EntityManager em = emf.createEntityManager();

            String queryString = new String("SELECT ca FROM CustomerAddress ca");

            System.out.println("Get all address TA begins");
            //logger.info("Get all customer TA begins");
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            Query q = em.createQuery(queryString);

            long queryStart = System.currentTimeMillis();

            ca = q.getResultList();

            long queryEnd = System.currentTimeMillis();


            tm.commit();
            long queryTime = queryEnd - queryStart;

            //logger.info("Get all customer TA ends");
            System.out.println("Get all address TA ends");
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


        return ca;
    }

    ;

    public void updateAdress(CustomerAddress ca) {
        try {
            logger.info("Update CustomerAddress TA begins");
            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();


            long queryStart = System.currentTimeMillis();

            CustomerAddress addressToUpdate = em.find(CustomerAddress.class, ca.getId());
            logger.info("Found CustomerAddress: " + addressToUpdate.toString());
            logger.info("Updating CustomerAddress...");
            addressToUpdate = ca;

            em.merge(addressToUpdate);

            long queryEnd = System.currentTimeMillis();


            em.flush();
            em.close();
            tm.commit();
            logger.info("Update CustomerAddress TA ends");

            long queryTime = queryEnd - queryStart;

            logger.info("CustomerAddress successfully persisted in " + queryTime + " ms.");

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

    public void deleteAdress(int CustomerAddressID) {

        try {
            //logger.info("Delete flightcustomer TA begins");
            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();


            // long queryStart = System.currentTimeMillis();

            CustomerAddress ad = em.find(CustomerAddress.class, CustomerAddressID);
            // logger.info("Found flightcustomer: " + ad.toString());
            // logger.info("Deleting flightcustomer...");


            em.remove(ad);

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

    public void deleteAllCustomerAddress() {

        List<Integer> addresses;

        try {


            addresses = getAllCustomerAddressIDs();


            logger.info("Delete all CustomerAddress TA begins");


            long queryStart = System.currentTimeMillis();

            for (Integer id : addresses) {

                deleteAdress(id);
            }


            long queryEnd = System.currentTimeMillis();


            logger.info("Delete all CustomerAddress TA ends");
            long queryTime = queryEnd - queryStart;

            logger.info(addresses.size() + " CustomerAddress successfully deleted in " + queryTime + " ms.");

            String writeToFile = new String(Config.PERSISTENCE_UNIT_NAME + " DELETE: " + addresses.size() + " " + queryTime + "\n");
            Files.write(Paths.get(Config.LOG_STORAGE_LOCATION), writeToFile.getBytes(), CREATE, APPEND);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public List<Integer> getAllCustomerAddressIDs() {

        List<CustomerAddress> caIDs = new ArrayList<CustomerAddress>();
        List<Integer> returnList = new ArrayList<Integer>();

        try {
            EntityManager em = emf.createEntityManager();

            //String queryString = new String("SELECT c.C_ID FROM Customer c");
            /*
            some datastores (that uses indexes like Redis, Infinispan, Cassandra) cannot operate on single return values,
            they always need to return the entire class
             */

            String queryString = new String("SELECT c FROM CustomerAddress c");
            Query q = em.createQuery(queryString);


            logger.info("Get all flightcustomerIDs TA begins");
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            long queryStart = System.currentTimeMillis();

            caIDs = q.getResultList();

            for (CustomerAddress ca : caIDs) {
                returnList.add(ca.getId());
            }


            long queryEnd = System.currentTimeMillis();


            em.flush();
            em.close();
            tm.commit();
            logger.info("Get all CustomerAddress TA ends");

            long queryTime = queryEnd - queryStart;

            logger.info("Found " + caIDs.size() + " CustomerAddress IDs in " + queryTime + " ms.");


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


}
