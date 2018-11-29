package de.hsma.jens.models;


import org.hibernate.annotations.GenericGenerator;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Indexed
@Table(name = "Flugzeuge")
public class Flugzeug implements Serializable {
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Id
    private Integer id;
    @Column
    private String typbezeichnung;
    @Column
    private Integer sitzplaetze_ersteklasse;
    @Column
    private Integer sitzplaetze_economy;
    @Column
    private Integer preis_ersteklasse;
    @Column
    private Integer preis_economy;
    @ManyToMany
    private Collection<Flight> flights = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypbezeichnung() {
        return typbezeichnung;
    }

    public void setTypbezeichnung(String typbezeichnung) {
        this.typbezeichnung = typbezeichnung;
    }

    public Integer getSitzplaetze_ersteklasse() {
        return sitzplaetze_ersteklasse;
    }

    public void setSitzplaetze_ersteklasse(Integer sitzplaetze_ersteklasse) {
        this.sitzplaetze_ersteklasse = sitzplaetze_ersteklasse;
    }

    public Integer getSitzplaetze_economy() {
        return sitzplaetze_economy;
    }

    public void setSitzplaetze_economy(Integer sitzplaetze_economy) {
        this.sitzplaetze_economy = sitzplaetze_economy;
    }

    public Integer getPreis_ersteklasse() {
        return preis_ersteklasse;
    }

    public void setPreis_ersteklasse(Integer preis_ersteklasse) {
        this.preis_ersteklasse = preis_ersteklasse;
    }

    public Integer getPreis_economy() {
        return preis_economy;
    }

    public void setPreis_economy(Integer preis_economy) {
        this.preis_economy = preis_economy;
    }

    public Collection<Flight> getFlights() {
        return flights;
    }

    public void setFlights(Collection<Flight> flights) {
        this.flights = flights;
    }

    @Override
    public String toString() {
        return "Flugzeug{" +
                "id=" + id +
                ", typbezeichnung='" + typbezeichnung + '\'' +
                ", sitzplaetze_ersteklasse=" + sitzplaetze_ersteklasse +
                ", sitzplaetze_economy=" + sitzplaetze_economy +
                ", preis_ersteklasse=" + preis_ersteklasse +
                ", preis_economy=" + preis_economy +
                ", flights=" + flights +
                '}';
    }
}




