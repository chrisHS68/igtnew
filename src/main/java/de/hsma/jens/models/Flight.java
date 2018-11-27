package de.hsma.jens.models;


import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Indexed
@Table(name = "Flights")
public class Flight implements Serializable {
    @Id
    private Integer id;
    @Column
    private Date ankuftszeit;
    @Column
    private Date abflugzeit;
    @ManyToMany(mappedBy = "customer")
    private Collection<FlightCustomer> customer = new ArrayList<>();
    @ManyToMany
    private Collection<Flightsegment> segments = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getAnkuftszeit() {
        return ankuftszeit;
    }

    public void setAnkuftszeit(Date ankuftszeit) {
        this.ankuftszeit = ankuftszeit;
    }

    public Date getAbflugzeit() {
        return abflugzeit;
    }

    public void setAbflugzeit(Date abflugzeit) {
        this.abflugzeit = abflugzeit;
    }

    public Collection<FlightCustomer> getCustomer() {
        return customer;
    }

    public void setCustomer(Collection<FlightCustomer> customer) {
        this.customer = customer;
    }

    public Collection<Flightsegment> getSegments() {
        return segments;
    }

    public void setSegments(Collection<Flightsegment> segments) {
        this.segments = segments;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", ankuftszeit=" + ankuftszeit +
                ", abflugzeit=" + abflugzeit +
                ", customer=" + customer +
                ", segments=" + segments +
                '}';
    }
}
