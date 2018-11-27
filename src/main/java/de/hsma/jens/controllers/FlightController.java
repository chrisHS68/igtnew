package de.hsma.jens.controllers;

import de.hsma.jens.models.Airport;
import de.hsma.jens.models.Flight;
import de.hsma.jens.tools.Config;
import org.apache.log4j.Logger;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.TransactionManager;
import java.util.List;

public class FlightController {
    private static Logger logger = Logger.getRootLogger();

    //accessing JBoss's Transaction can be done differently but this one works nicely
    TransactionManager tm = com.arjuna.ats.jta.TransactionManager.transactionManager();
    //build the EntityManagerFactory as you would build in in Hibernate ORM
    EntityManagerFactory emf = Persistence.createEntityManagerFactory(Config.PERSISTENCE_UNIT_NAME);

    //Inteface Methods
    public void createFlight(){

    };

    public int getFlight(int id){
        return 1;
    };

    public List<Flight> getAllFlights(){
        return null;
    };

    public void updateFlight(Flight fl){

    };
    public void updateFlight(List<Flight> fl){

    };

    public void deleteFlight(int id){

    };
    public void deleteAllFlight(){

    };
}
