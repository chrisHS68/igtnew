package de.hsma.jens.models;


import org.hibernate.search.annotations.Indexed;


import javax.persistence.*;
import java.io.Serializable;


@Entity
@Indexed
@Table(name = "CustomerAddress")
public class CustomerAddress implements Serializable {
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
    private FlightCustomer customer;

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

    public FlightCustomer getCustomer() {
        return customer;
    }

    public void setCustomer(FlightCustomer customer) {
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
