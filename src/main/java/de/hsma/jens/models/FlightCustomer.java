package de.hsma.jens.models;

import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;


@Entity
@Indexed
@Table(name = "FlightCustomers")
public class FlightCustomer implements Serializable {
    @Id
    private Integer id;
    @Column
    private Integer gesamtmeilen;
    @Column
    private Integer jahresmeilen;
    @Enumerated(EnumType.STRING)
    @Column
    private Status customerStatus;
    @OneToMany
    private Collection<CustomerAddress> customerAddresses = new ArrayList<>();
    @ManyToMany
    private Collection<PhoneType> phones = new ArrayList<>();
    @ManyToMany
    private  Collection<Flight> flight = new ArrayList<>();


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGesamtmeilen() {
        return gesamtmeilen;
    }

    public void setGesamtmeilen(Integer gesamtmeilen) {
        this.gesamtmeilen = gesamtmeilen;
    }

    public Integer getJahresmeilen() {
        return jahresmeilen;
    }

    public void setJahresmeilen(Integer jahresmeilen) {
        this.jahresmeilen = jahresmeilen;
    }

    public Status getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(Status customerStatus) {
        this.customerStatus = customerStatus;
    }

    public Collection<CustomerAddress> getCustomerAddresses() {
        return customerAddresses;
    }

    public void setCustomerAddresses(Collection<CustomerAddress> customerAddresses) {
        this.customerAddresses = customerAddresses;
    }

    public Collection<PhoneType> getPhones() {
        return phones;
    }

    public void setPhones(Collection<PhoneType> phones) {
        this.phones = phones;
    }

    public Collection<Flight> getFlight() {
        return flight;
    }

    public void setFlight(Collection<Flight> flight) {
        this.flight = flight;
    }

    @Override
    public String toString() {
        return "FlightCustomer{" +
                "id=" + id +
                ", gesamtmeilen=" + gesamtmeilen +
                ", jahresmeilen=" + jahresmeilen +
                ", customerStatus=" + customerStatus +
                ", customerAddresses=" + customerAddresses +
                ", phones=" + phones +
                ", flight=" + flight +
                '}';
    }
}