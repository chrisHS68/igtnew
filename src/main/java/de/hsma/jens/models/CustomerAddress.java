package de.hsma.jens.models;


import org.hibernate.annotations.GenericGenerator;
import org.hibernate.search.annotations.Indexed;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;


@Entity
@Indexed
@Table(name = "CustomerAddress")
public class CustomerAddress implements Serializable {
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Id
    private Integer id;
    @Column
    private String strasse;
    @Column
    private String plz;
    @Column
    private String ort;
    @Column
    private String hausnummer;
    @OneToMany
    private Collection<FlightCustomer> customer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public String getPlz() {
        return plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public String getHausnummer() {
        return hausnummer;
    }

    public void setHausnummer(String hausnummer) {
        this.hausnummer = hausnummer;
    }

    public Collection<FlightCustomer> getCustomer() {
        return customer;
    }

    public void setCustomer(Collection<FlightCustomer> customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "CustomerAddress{" +
                "id=" + id +
                ", strasse='" + strasse + '\'' +
                ", plz='" + plz + '\'' +
                ", ort='" + ort + '\'' +
                ", hausnummer='" + hausnummer + '\'' +
                ", customer=" + customer +
                '}';
    }
}
