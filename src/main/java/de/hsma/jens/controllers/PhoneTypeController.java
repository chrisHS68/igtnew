package de.hsma.jens.controllers;

import de.hsma.jens.models.FlightCustomer;
import de.hsma.jens.models.PhoneType;
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

public class PhoneTypeController {
    private static Logger logger = Logger.getRootLogger();

    //accessing JBoss's Transaction can be done differently but this one works nicely
    TransactionManager tm = com.arjuna.ats.jta.TransactionManager.transactionManager();
    //build the EntityManagerFactory as you would build in in Hibernate ORM
    EntityManagerFactory emf = Persistence.createEntityManagerFactory(Config.PERSISTENCE_UNIT_NAME);

    //Inteface Methods
    public void createPhoneType(List<PhoneType> ptList){
        try {
            logger.info("Create PhoneType TA begins");
            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            long queryStart = System.currentTimeMillis();

            for (PhoneType pt : ptList) {
                em.persist(pt);
            }

            long queryEnd = System.currentTimeMillis();

            em.flush();
            em.close();
            tm.commit();


            logger.info("Create PhoneType TA ends");
            long queryTime = queryEnd - queryStart;

            logger.info(ptList.size() + " PhoneType persisted in DB in " + queryTime + " ms.");

            String writeToFile = new String(Config.PERSISTENCE_UNIT_NAME + " CREATE: " + ptList.size() + " " + queryTime + "\n");

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

    public PhoneType getPhoneType(int phoneTypeID){


        PhoneType pt = null;


        try {
            EntityManager em = emf.createEntityManager();
            tm.begin();


            pt = em.find(PhoneType.class, phoneTypeID);
            logger.info("Found Phonetype: " + pt.toString());


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


        return pt;

    };

    public List<PhoneType> getAllPhoneTypes(){

        List<PhoneType> phoneTypes = new ArrayList<PhoneType>();
        try {
            EntityManager em = emf.createEntityManager();

            String queryString = new String("SELECT c FROM PhoneType c");

            System.out.println("Get all PhoneType TA begins");
            logger.info("Get all customer TA begins");
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            Query q = em.createQuery(queryString);

            long queryStart = System.currentTimeMillis();

            phoneTypes = q.getResultList();

            long queryEnd = System.currentTimeMillis();


            tm.commit();
            long queryTime = queryEnd - queryStart;

            logger.info("Get all customer TA ends");
            System.out.println("Get all Phonetype TA ends");
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


        return phoneTypes;
    };

    public void updatePhoneType(PhoneType pt){

        try {
            logger.info("Update PhoneType TA begins");
            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();


            long queryStart = System.currentTimeMillis();

            PhoneType phoneTypeToUpdate = em.find(PhoneType.class, pt.getId());
            logger.info("Found PhoneType: " + phoneTypeToUpdate.toString());
            logger.info("Updating PhoneType...");
            phoneTypeToUpdate = pt;

            em.merge(phoneTypeToUpdate);

            long queryEnd = System.currentTimeMillis();


            em.flush();
            em.close();
            tm.commit();
            logger.info("Update PhoneType TA ends");

            long queryTime = queryEnd - queryStart;

            logger.info("PhoneType successfully persisted in " + queryTime + " ms.");

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

    public void updatePhoneType(List<PhoneType> ptList){
        try {
            EntityManager em = emf.createEntityManager();
            logger.info("Update Phonetype TA begins");
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            long queryStart = System.currentTimeMillis();

            for (PhoneType pt : ptList) {

                em.merge(pt);

            }

            long queryEnd = System.currentTimeMillis();


            em.flush();
            em.close();
            tm.commit();
            logger.info("Update Phonetype TA ends");

            long queryTime = queryEnd - queryStart;

            logger.info("Updates of " + ptList.size() + " phonetype successfully persisted in " + queryTime + " ms.");

            String writeToFile = new String(Config.PERSISTENCE_UNIT_NAME + " UPDATE: " + ptList.size() + " " + queryTime + "\n");
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

    public void deletePhoneType(int PhoneTypeID){
        try {
            logger.info("Delete PhoneType TA begins");
            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();


            long queryStart = System.currentTimeMillis();

            PhoneType phoneType = em.find(PhoneType.class, PhoneTypeID);
            logger.info("Found PhoneType: " + phoneType.toString());
            logger.info("Deleting PhoneType...");


            em.remove(phoneType);

            long queryEnd = System.currentTimeMillis();


            em.flush();
            em.close();
            tm.commit();
            logger.info("Delete PhoneType TA ends");

            long queryTime = queryEnd - queryStart;

            logger.info("PhoneType successfully deleted in " + queryTime + " ms.");


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
    public void deleteAllPhoneType(){
        List<Integer> phoneTypesIDs;

        try {


            phoneTypesIDs = getAllPhoneTypeIDs();


            logger.info("Delete all customers TA begins");


            long queryStart = System.currentTimeMillis();

            for (Integer id : phoneTypesIDs) {

                deletePhoneType(id);
            }


            long queryEnd = System.currentTimeMillis();


            logger.info("Delete all PhoneType TA ends");
            long queryTime = queryEnd - queryStart;

            logger.info(phoneTypesIDs.size() + " PhoneType  successfully deleted in " + queryTime + " ms.");

            String writeToFile = new String(Config.PERSISTENCE_UNIT_NAME + " DELETE: " + phoneTypesIDs.size() + " " + queryTime + "\n");
            Files.write(Paths.get(Config.LOG_STORAGE_LOCATION), writeToFile.getBytes(), CREATE, APPEND);


        } catch (IOException e) {
            e.printStackTrace();
        }

    };

    public List<Integer> getAllPhoneTypeIDs() {

        List<PhoneType> phoneTypesIDs = new ArrayList<PhoneType>();
        List<Integer> returnList = new ArrayList<Integer>();

        try {
            EntityManager em = emf.createEntityManager();

            //String queryString = new String("SELECT c.C_ID FROM Customer c");
            /*
            some datastores (that uses indexes like Redis, Infinispan, Cassandra) cannot operate on single return values,
            they always need to return the entire class
             */

            String queryString = new String("SELECT c FROM PhoneType c");
            Query q = em.createQuery(queryString);


            logger.info("Get all getAllPhoneTypeIDs TA begins");
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            long queryStart = System.currentTimeMillis();

            phoneTypesIDs = q.getResultList();

            for (PhoneType pt : phoneTypesIDs) {
                returnList.add(pt.getId());
            }


            long queryEnd = System.currentTimeMillis();


            em.flush();
            em.close();
            tm.commit();
            logger.info("Get all PhoneTypeIDs TA ends");

            long queryTime = queryEnd - queryStart;

            logger.info("Found " + phoneTypesIDs.size() + " flightcustomer IDs in " + queryTime + " ms.");


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
