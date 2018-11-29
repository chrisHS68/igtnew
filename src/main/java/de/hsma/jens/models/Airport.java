package de.hsma.jens.models;


import org.hibernate.annotations.GenericGenerator;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Indexed
@Table(name = "Airports")
public class Airport implements Serializable {
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Id
    private Integer id;
    @Column
    private String kuerzel;
    @Column
    private String name;
    @Column
    private String Land;

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

    @Override
    public String toString() {
        return "Airport{" +
                "id=" + id +
                ", kuerzel='" + kuerzel + '\'' +
                ", name='" + name + '\'' +
                ", Land='" + Land + '\'' +
                '}';
    }
}
