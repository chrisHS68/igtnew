package de.hsma.jens.models;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Indexed
@Table(name = "PHONE_TYPE")
public class PhoneType implements Serializable {
/*    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")*/
    @Id
    private Integer id;
    @Column
    private Long number;
    @ManyToMany (mappedBy = "phones")
    private List<FlightCustomer> customers = new ArrayList<FlightCustomer>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public List<FlightCustomer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<FlightCustomer> customers) {
        this.customers = customers;
    }
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "-" + getId();
    }

}
