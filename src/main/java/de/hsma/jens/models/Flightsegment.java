package de.hsma.jens.models;


import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Indexed
@Table(name = "Flightsegments")
public class Flightsegment {
    @Id
    private Integer id;
    @Column
    private String name;
    @Column
    private Integer strecke;
    @ManyToMany(mappedBy = "segment")
    private Collection<Flight> flights = new ArrayList<>();
    @OneToMany
    private Collection<Airport> zielflughafen = new ArrayList<>();
    @OneToMany
    private Collection<Airport> abflughafen = new ArrayList<>();
}
