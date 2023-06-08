package com.csgames.exodus.model;

import javax.persistence.*;


@Entity
@Table(name="RebelLocation")
public class RebelLocation {

    private long id;
    private String country;
    private String city;
    private String name;
    private String address;

    public RebelLocation() {}

    public RebelLocation(String country, String city, String name, String address) {
        this.country = country;
        this.city = city;
        this.name = name;
        this.address = address;
    }

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    @Column
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Column
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
