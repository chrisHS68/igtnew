package de.hsma.jens.controllers;

import com.sun.org.apache.xpath.internal.functions.FuncGenerateId;
import de.hsma.jens.models.FlightCustomer;
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

public class FlugzeugController {
    private static Logger logger = Logger.getRootLogger();

    //accessing JBoss's Transaction can be done differently but this one works nicely
    TransactionManager tm = com.arjuna.ats.jta.TransactionManager.transactionManager();
    //build the EntityManagerFactory as you would build in in Hibernate ORM
    EntityManagerFactory emf = Persistence.createEntityManagerFactory(Config.PERSISTENCE_UNIT_NAME);

    //Inteface Methods
    public void createFlugzeug(List<Flugzeug> flugzeugList) {
        try {
            logger.info("Create Flugzeug TA begins");
            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            long queryStart = System.currentTimeMillis();

            for (Flugzeug fl : flugzeugList) {
                em.persist(fl);
            }

            long queryEnd = System.currentTimeMillis();

            em.flush();
            em.close();
            tm.commit();


            logger.info("Create Flugzeug TA ends");
            long queryTime = queryEnd - queryStart;

            logger.info(flugzeugList.size() + " Flugzeug persisted in DB in " + queryTime + " ms.");

            String writeToFile = new String(Config.PERSISTENCE_UNIT_NAME + " CREATE: " + flugzeugList.size() + " " + queryTime + "\n");

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


    public List<Flugzeug> getAllFlugzeuge() {

        List<Flugzeug> fl = new ArrayList<Flugzeug>();
        try {
            EntityManager em = emf.createEntityManager();

            String queryString = new String("SELECT fl FROM Flugzeug fl");

            System.out.println("Get all Flugzeuge TA begins");
            //logger.info("Get all Flugzeuge TA begins");
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            Query q = em.createQuery(queryString);

            long queryStart = System.currentTimeMillis();

            fl = q.getResultList();

            long queryEnd = System.currentTimeMillis();


            tm.commit();
            long queryTime = queryEnd - queryStart;

            //logger.info("Get all Flugzeuge TA ends");
            System.out.println("Get all Flugzeuge TA ends");
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


    public void updateFlugzeuge(Flugzeug fl) {

        try {
            logger.info("Update Flugzeug TA begins");
            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();


            long queryStart = System.currentTimeMillis();

            Flugzeug flugzeugToUpdate = em.find(Flugzeug.class, fl.getId());
            logger.info("Found Flugzeug: " + flugzeugToUpdate.toString());
            logger.info("Updating Flugzeug...");
            flugzeugToUpdate = fl;

            em.merge(flugzeugToUpdate);

            long queryEnd = System.currentTimeMillis();


            em.flush();
            em.close();
            tm.commit();
            logger.info("Update Flugzeug TA ends");

            long queryTime = queryEnd - queryStart;

            logger.info("Flugzeug successfully persisted in " + queryTime + " ms.");

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

    public void updateFlugzeug(List<Flugzeug> flugzeugList) {

        try {
            EntityManager em = emf.createEntityManager();
            logger.info("Update Flugzeug TA begins");
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            long queryStart = System.currentTimeMillis();

            for (Flugzeug fl : flugzeugList) {

                em.merge(fl);

            }

            long queryEnd = System.currentTimeMillis();


            em.flush();
            em.close();
            tm.commit();
            logger.info("Update Flugzeug TA ends");

            long queryTime = queryEnd - queryStart;

            logger.info("Updates of " + flugzeugList.size() + " Flugzeug successfully persisted in " + queryTime + " ms.");

            String writeToFile = new String(Config.PERSISTENCE_UNIT_NAME + " UPDATE: " + flugzeugList.size() + " " + queryTime + "\n");
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


    public void deleteFlugzeug(int FlugzeugID) {

        try {
            //logger.info("Delete Flugzeug TA begins");
            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();


            // long queryStart = System.currentTimeMillis();

            Flugzeug flugzeug = em.find(Flugzeug.class, FlugzeugID);
            // logger.info("Found Flugzeug: " + flugzeug.toString());
            // logger.info("Deleting Flugzeug...");


            em.remove(flugzeug);

            //  long queryEnd = System.currentTimeMillis();


            em.flush();
            em.close();
            tm.commit();
            //logger.info("Delete Flugzeug TA ends");

            //  long queryTime = queryEnd - queryStart;

            // logger.info("Flugzeug successfully deleted in " + queryTime + " ms.");


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


    public List<Flugzeug> getALLFlugzeug() {
        List<Flugzeug> flugzeug = new ArrayList<Flugzeug>();


        try {
            EntityManager em = emf.createEntityManager();

            String queryString = new String("SELECT f FROM Flugzeug f");

            logger.info("Get all Flugzeug TA begins");
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();


            Query q = em.createQuery(queryString);

            long queryStart = System.currentTimeMillis();

            flugzeug = q.getResultList();

            long queryEnd = System.currentTimeMillis();


            em.flush();
            em.close();
            tm.commit();

            logger.info("Get all Flugzeug TA ends");

            long queryTime = queryEnd - queryStart;

            logger.info("Found " + flugzeug.size() + " Flugzeuge in " + queryTime + " ms.");

            String writeToFile = new String(Config.PERSISTENCE_UNIT_NAME + " READ  : " + flugzeug.size() + " " + queryTime + "\n");
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


        return flugzeug;

    }

    public void deleteAllFlugzeuge() {

        List<Integer> flugzeug;

        try {


            flugzeug = getAllFlugzeugIDs();


            logger.info("Delete all Flugzeuge TA begins");


            long queryStart = System.currentTimeMillis();

            for (Integer id : flugzeug) {

                deleteFlugzeug(id);
            }


            long queryEnd = System.currentTimeMillis();


            logger.info("Delete all Flugzeuge TA ends");
            long queryTime = queryEnd - queryStart;

            logger.info(flugzeug.size() + " Flugzeuge successfully deleted in " + queryTime + " ms.");

            String writeToFile = new String(Config.PERSISTENCE_UNIT_NAME + " DELETE: " + flugzeug.size() + " " + queryTime + "\n");
            Files.write(Paths.get(Config.LOG_STORAGE_LOCATION), writeToFile.getBytes(), CREATE, APPEND);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public List<Integer> getAllFlugzeugIDs() {

        List<Flugzeug> flugzeugIDs = new ArrayList<Flugzeug>();
        List<Integer> returnList = new ArrayList<Integer>();

        try {
            EntityManager em = emf.createEntityManager();

            //String queryString = new String("SELECT f.Flugzeug_ID FROM Flugzeug f");
            /*
            some datastores (that uses indexes like Redis, Infinispan, Cassandra) cannot operate on single return values,
            they always need to return the entire class
             */

            String queryString = new String("SELECT f FROM Flugzeug f");
            Query q = em.createQuery(queryString);


            logger.info("Get all FlugzeugIDs TA begins");
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            long queryStart = System.currentTimeMillis();

            flugzeugIDs = q.getResultList();

            for (Flugzeug flugzeug : flugzeugIDs) {
                returnList.add(flugzeug.getId());
            }


            long queryEnd = System.currentTimeMillis();


            em.flush();
            em.close();
            tm.commit();
            logger.info("Get all flugzeugIDs TA ends");

            long queryTime = queryEnd - queryStart;

            logger.info("Found " + flugzeugIDs.size() + " FlugzeugIDs in " + queryTime + " ms.");


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

    public Flugzeug getFlugzeug(int flugzeugID) {


        Flugzeug flugzeug = null;


        try {
            EntityManager em = emf.createEntityManager();
            tm.begin();


            flugzeug = em.find(Flugzeug.class, flugzeugID);
            // logger.info("Found Flugzeug: " + flugzeug.toString());


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


        return flugzeug;


    }

    public Integer getAllFlugzeugeAsList() {


        ObjectOutputStream oos = null;
        FileOutputStream fos = null;
        List<Integer> numberOfFlugzeuge = null;


        try {


            fos = new FileOutputStream(Config.PERSIST_STORAGE_OUTPUT_LOCATION);
            oos = new ObjectOutputStream(fos);

            EntityManager em = emf.createEntityManager();


            String queryString = new String("FROM Flugzeuge");


            javax.persistence.Query query = em.createQuery(queryString);

            Session session = em.unwrap(Session.class);

            /*
            Hibernate OGM: not implemented yet
             */

            ScrollableResults results = session
                    .createQuery("FROM Flugzeuge")
                    .setCacheMode(CacheMode.IGNORE)
                    .scroll(ScrollMode.FORWARD_ONLY);


            numberOfFlugzeuge = getAllFlugzeugIDs();

            logger.info("Get all Flugzeuge TA begins");
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();


            long queryStart = System.currentTimeMillis();


            while (results.next()) {
                Flugzeug flugzeuge = (Flugzeug) results.get(0);
                flugzeuge.toString();
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

            logger.info("Get all Flugzeuge TA ends");

            long queryTime = queryEnd - queryStart;

            logger.info("Found " + numberOfFlugzeuge.size() + " Flugzeuge in " + queryTime + " ms.");

            String writeToFile = new String(Config.PERSISTENCE_UNIT_NAME + " READ  : " + numberOfFlugzeuge.size() + " " + queryTime + "\n");
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

        return numberOfFlugzeuge.size();


    }

}
