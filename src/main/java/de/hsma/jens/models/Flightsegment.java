package de.hsma.jens.models;


import org.hibernate.annotations.GenericGenerator;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Indexed
@Table(name = "FLIGHTSEGMENT")
public class Flightsegment {
/*    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")*/
    @Id
    private Integer id;
    @Column
    private String name;
    @Column
    private Integer strecke;
    @ManyToMany (mappedBy = "segments")
    private List<Flight> flights = new ArrayList<Flight>();
    @ManyToOne
    private Airport zielflughafen;
    @ManyToOne
    private Airport abflughafen;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStrecke() {
        return strecke;
    }

    public void setStrecke(Integer strecke) {
        this.strecke = strecke;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    public Airport getZielflughafen() {
        return zielflughafen;
    }

    public void setZielflughafen(Airport zielflughafen) {
        this.zielflughafen = zielflughafen;
    }

    public Airport getAbflughafen() {
        return abflughafen;
    }

    public void setAbflughafen(Airport abflughafen) {
        this.abflughafen = abflughafen;
    }

    @Override
    public String toString() {
        return "Flightsegment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", strecke=" + strecke +
                ", flights=" + flights +
                ", zielflughafen=" + zielflughafen +
                ", abflughafen=" + abflughafen +
                '}';
    }
}
