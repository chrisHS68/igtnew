package de.hsma.jens.models;


import org.hibernate.annotations.GenericGenerator;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Indexed
@Table(name = "CUSTOMER_ADDRESS")
public class CustomerAddress implements Serializable {
/*    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")*/
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
    @OneToOne
    private FlightCustomer customer;

    public FlightCustomer getCustomer() {
        return customer;
    }

    public void setCustomer(FlightCustomer customer) {
        this.customer = customer;
    }

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
