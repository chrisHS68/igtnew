package de.hsma.jens.models;


import org.hibernate.annotations.GenericGenerator;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Indexed
@Table(name = "Flightsegments")
public class Flightsegment {
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Id
    private Integer id;
    @Column
    private String name;
    @Column
    private Integer strecke;
    @ManyToMany
    private Collection<Flight> flights = new ArrayList<>();
    @OneToMany
    private Collection<Airport> zielflughafen = new ArrayList<>();
    @OneToMany
    private Collection<Airport> abflughafen = new ArrayList<>();
}
