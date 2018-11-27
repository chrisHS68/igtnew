package de.hsma.jens.controllers;

import de.hsma.jens.models.CustomerAddress;
import de.hsma.jens.tools.Config;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.TransactionManager;
import java.util.List;

public class CustomerAdressController {

    //accessing JBoss's Transaction can be done differently but this one works nicely
    TransactionManager tm = com.arjuna.ats.jta.TransactionManager.transactionManager();
    //build the EntityManagerFactory as you would build in in Hibernate ORM
    EntityManagerFactory emf = Persistence.createEntityManagerFactory(Config.PERSISTENCE_UNIT_NAME);

    //Inteface Methods
    public void createAdress(){

    };

    public int getAdress(int id){

        return 1;
    };

    public List<CustomerAddress> getAllAdress(){

        return null;
    };

    public void updateAdress(CustomerAddress a){

    };

    public void updateAdress(List<CustomerAddress> a){

    };

    public void deleteAdress(int id){

    };

    public void deleteAllAdress(){

    };

}
