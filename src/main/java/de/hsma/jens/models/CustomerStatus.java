package de.hsma.jens.models;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;


/**
 * Falls unnötig löschen!!!
 **/
@Entity
@Indexed
@Table(name = "Flights")
public class CustomerStatus implements Serializable {
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Id
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column
    private Status customerStatus;

    @OneToMany
    private Collection<FlightCustomer> customer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Status getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(Status customerStatus) {
        this.customerStatus = customerStatus;
    }

    public Collection<FlightCustomer> getCustomer() {
        return customer;
    }

    public void setCustomer(Collection<FlightCustomer> customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "CustomerStatus{" +
                "id=" + id +
                ", customerStatus=" + customerStatus +
                ", customer=" + customer +
                '}';
    }
}
