package de.hsma.jens.controllers;

import de.hsma.jens.models.Airport;
import de.hsma.jens.models.FlightCustomer;
import de.hsma.jens.tools.Config;
import org.apache.log4j.Logger;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.TransactionManager;
import java.util.List;

public class FlightCustomerController {
    private static Logger logger = Logger.getRootLogger();

    //accessing JBoss's Transaction can be done differently but this one works nicely
    TransactionManager tm = com.arjuna.ats.jta.TransactionManager.transactionManager();
    //build the EntityManagerFactory as you would build in in Hibernate ORM
    EntityManagerFactory emf = Persistence.createEntityManagerFactory(Config.PERSISTENCE_UNIT_NAME);

    //Inteface Methods
    public void createFlightCustomer(){

    };

    public int getFlightCustomer(int id){
        return 1;
    };

    public List<FlightCustomer> getAllFlightCustomers(){
        return null;
    };

    public void updateFlightCustomer(FlightCustomer fc){

    };
    public void FlightCustomer(List<FlightCustomer> fc){

    };

    public void deleteFlightCustomer(int id){

    };
    public void deleteAllFlightCustomer(){

    };
}
