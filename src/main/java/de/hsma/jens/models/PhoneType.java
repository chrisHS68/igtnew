package de.hsma.jens.models;

import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;


@Entity
@Indexed
@Table(name = "PhoneTypes")
public class PhoneType implements Serializable {
    @Id
    private Integer id;
    @Column
    private Integer number;
    @ManyToMany(mappedBy = "phones")
    private Collection<FlightCustomer> phones = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Collection<FlightCustomer> getPhones() {
        return phones;
    }

    public void setPhones(Collection<FlightCustomer> phones) {
        this.phones = phones;
    }

    @Override
    public String toString() {
        return "PhoneType{" +
                "id=" + id +
                ", number=" + number +
                ", phones=" + phones +
                '}';
    }
}
