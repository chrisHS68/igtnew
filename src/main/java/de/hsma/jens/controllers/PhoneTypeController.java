package de.hsma.jens.controllers;

import de.hsma.jens.models.PhoneType;
import de.hsma.jens.tools.Config;
import org.apache.log4j.Logger;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.TransactionManager;
import java.util.List;

public class PhoneTypeController {
    private static Logger logger = Logger.getRootLogger();

    //accessing JBoss's Transaction can be done differently but this one works nicely
    TransactionManager tm = com.arjuna.ats.jta.TransactionManager.transactionManager();
    //build the EntityManagerFactory as you would build in in Hibernate ORM
    EntityManagerFactory emf = Persistence.createEntityManagerFactory(Config.PERSISTENCE_UNIT_NAME);

    //Inteface Methods
    public void createPhoneType(){

    };

    public int getPhoneType(int id){
        return 1;
    };

    public List<PhoneType> getAllPhoneTypes(){
        return null;
    };

    public void updatePhoneType(PhoneType pt){

    };
    public void updatePhoneType(List<PhoneType> pt){

    };

    public void deletePhoneType(int id){

    };
    public void deletePhoneType(){

    };

}
