package de.hsma.jens.models;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Indexed
@Table(name = "F_CUSTOMER")
public class FlightCustomer implements Serializable {
/*    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")*/
    @Id
    private Integer id;
    @Column
    private String name;
    @Column
    private Integer gesamtmeilen;
    @Column
    private Integer jahresmeilen;
    @Enumerated(EnumType.STRING)
    @Column
    private Status customerStatus;
    @OneToOne
    CustomerAddress customerAddress;
    @ManyToMany
    private List<PhoneType> phones = new ArrayList<PhoneType>();
    @ManyToMany
    private  List<Flight> flights = new ArrayList<Flight>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
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

    public CustomerAddress getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(CustomerAddress customerAddress) {
        this.customerAddress = customerAddress;
    }


    public List<PhoneType> getPhones() {
        return phones;
    }

    public void setPhones(List<PhoneType> phones) {
        this.phones = phones;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    @Override
    public String toString() {
        return "FlightCustomer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gesamtmeilen=" + gesamtmeilen +
                ", jahresmeilen=" + jahresmeilen +
                ", customerStatus=" + customerStatus +
                ", customerAddress=" + customerAddress +
                ", phones=" + phones +
                ", flights=" + flights +
                '}';
    }

}