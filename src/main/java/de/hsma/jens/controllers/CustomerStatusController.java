package de.hsma.jens.controllers;

import de.hsma.jens.models.CustomerStatus;
import de.hsma.jens.tools.Config;
import org.apache.log4j.Logger;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.TransactionManager;
import java.util.List;

public class CustomerStatusController {

    private static Logger logger = Logger.getRootLogger();

    //accessing JBoss's Transaction can be done differently but this one works nicely
    TransactionManager tm = com.arjuna.ats.jta.TransactionManager.transactionManager();
    //build the EntityManagerFactory as you would build in in Hibernate ORM
    EntityManagerFactory emf = Persistence.createEntityManagerFactory(Config.PERSISTENCE_UNIT_NAME);

    //Interface Methods
    public void createCustomerStatus(){

    };

    public int getAirport(int id){
        return 1;
    };

    public List<CustomerStatus> getAllCustomerStatus(){
        return null;
    };

    public void updateCustomerStatus(CustomerStatus cs){

    };
    public void updateCustomerStatus(List<CustomerStatus> cs){

    };

    public void deleteCustomerStatus(int id){

    };
    public void deleteAllCustomerStatus(){

    };
}
