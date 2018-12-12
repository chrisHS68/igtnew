package de.hsma.jens.models;


import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Indexed
@Table(name = "FLIGHT")
public class Flight implements Serializable {
    /*    @GeneratedValue(generator="increment")
        @GenericGenerator(name="increment", strategy = "increment")*/
    @Id
    private Integer id;
    @Column
    private Date ankuftszeit;
    @Column
    private Date abflugzeit;
    @ManyToOne
    Flugzeug flugzeug;
    @ManyToMany(mappedBy = "flights")
    private List<FlightCustomer> customer = new ArrayList<>();
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Flightsegment> segments = new ArrayList<>();

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

    public Flugzeug getFlugzeug() {
        return flugzeug;
    }

    public void setFlugzeug(Flugzeug flugzeug) {
        this.flugzeug = flugzeug;
    }

    public List<FlightCustomer> getCustomer() {
        return customer;
    }

    public void setCustomer(List<FlightCustomer> customer) {
        this.customer = customer;
    }

    public List<Flightsegment> getSegments() {
        return segments;
    }

    public void setSegments(List<Flightsegment> segments) {
        this.segments = segments;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "-" + getId();
    }

}
