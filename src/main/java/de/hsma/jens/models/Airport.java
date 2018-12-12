package de.hsma.jens.models;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

@Entity
@Indexed
@Table(name = "AIRPORT")
public class Airport implements Serializable {
    /*    @GeneratedValue(generator = "increment")
        @GenericGenerator(name = "increment", strategy = "increment")*/
    @Id
    private Integer id;
    @Column
    private String kuerzel;
    @Column
    private String name;
    @Column
    private String Land;
    @OneToMany(mappedBy = "zielflughafen")
    private List<Flightsegment> zielflughafen = new ArrayList<Flightsegment>();
    @OneToMany(mappedBy = "abflughafen")
    private List<Flightsegment> abflughafen = new ArrayList<Flightsegment>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKuerzel() {
        return kuerzel;
    }

    public void setKuerzel(String kuerzel) {
        this.kuerzel = kuerzel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLand() {
        return Land;
    }

    public void setLand(String land) {
        Land = land;
    }

    public List<Flightsegment> getZielflughafen() {
        return zielflughafen;
    }

    public void setZielflughafen(List<Flightsegment> zielflughafen) {
        this.zielflughafen = zielflughafen;
    }

    public List<Flightsegment> getAbflughafen() {
        return abflughafen;
    }

    public void setAbflughafen(List<Flightsegment> abflughafen) {
        this.abflughafen = abflughafen;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "-" + getId();
    }
}
